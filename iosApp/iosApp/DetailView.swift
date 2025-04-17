//
//  DetailView.swift
//  iosApp
//
//  Created by admin on 16/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI
import Combine

struct DetailView: View {
    //@ObservedObject var viewModel: WeatherViewModelWrapper
    @State private var weatherText: String = ""
    var location: String
    var lat: String
    var long: String

    init(location: String, lat: String, long: String) {
        self.location = location
        self.lat = lat
        self.long = long
        //self.viewModel = WeatherViewModelWrapper(lat: lat, long: long)
    }
    
    
    var body: some View {
        ScrollView {
            VStack(alignment: .center) {
                Text(location)
                    .font(.title)
                    .fontWeight(.bold)
                    .padding(.top, 4)
                if(weatherText != ""){
                    //Text(weatherText)
                    var weatherdata  = returnData(weatherText : weatherText)
                    if(weatherdata != nil){
                        WeatherView(weather: weatherdata! )
                    }
                    
                }
                
                
               
                
//                switch vm.result {
//                case is NetworkResultLoading:
//                    Text("Loading...")
//
//                case is NetworkResultError:
//                    VStack {
//                        //Text("Error: \(message)")
//                        Text("Latitude: \(lat)")
//                        Text("Longitude: \(long)")
//                    }
//
//                case let success as NetworkResultSuccess:
//                    VStack(alignment: .leading, spacing: 8) {
////                        Text("Latitude: \(weather.latitude ?? "")")
////                        Text("Longitude: \(weather.longitude ?? "")")
////                        Text("Temperature: \(weather.current?.temperature_2m ?? "") \(weather.current_units?.temperature_2m ?? "")")
////                        Text("Wind Speed: \(weather.current?.wind_speed_10m ?? "") \(weather.current_units?.wind_speed_10m ?? "")")
//
//                        Text("Hourly:")
//                            .font(.headline)
//                            .padding(.top)
//
////                        ForEach(Array(zip3(weather.hourly?.temperature_2m ?? [],
////                                           weather.hourly?.wind_speed_10m ?? [],
////                                           weather.hourly?.relative_humidity_2m ?? [])), id: \.0) { temp, wind, humidity in
////                            DetailItem(
////                                temperature: "\(temp) \(weather.hourly_units?.temperature_2m ?? "")",
////                                windSpeed: "\(wind) \(weather.hourly_units?.wind_speed_10m ?? "")",
////                                relativeHumidity: "\(humidity) \(weather.hourly_units?.relative_humidity_2m ?? "")"
////                            )
////                        }
//                    }
//                    .padding()
                }
        }.padding().background(Color.white).task {
            apiCall(weatherRespo: WeatherRepositoryImpl(), text: $weatherText)
        }
    }
    func returnData(weatherText : String) -> WeatherResponseIos? {
        if let jsonData = weatherText.data(using: .utf8) {
            do {
                let user = try JSONDecoder().decode(WeatherResponseIos.self, from: jsonData)
                print("Name: \(user.latitude), Age: \(user.longitude)")
                return user
            } catch {
                print("Decoding failed: \(error)")
                return nil
            }
        }else {
            return nil
        }
    }
    
    var weatherRespo = WeatherRepositoryImpl()
    func apiCall(weatherRespo: WeatherRepositoryImpl, text: Binding<String>) {
            Task {
                do {
                    let result = try await weatherRespo.getWeather(lat : "37.7749", long: "-122.4194")
                    print(result)
                    text.wrappedValue = result
                } catch {
                    text.wrappedValue = "Error: \(error.localizedDescription)"
                    print(error.localizedDescription)
                }
            }
        }
}


func zip3<T, U, V>(_ xs: [T], _ ys: [U], _ zs: [V]) -> [(T, U, V)] {
    let count = min(xs.count, ys.count, zs.count)
    return (0..<count).map { (xs[$0], ys[$0], zs[$0]) }
}


struct DetailItem: View {
    var temperature: String
    var windSpeed: String
    var relativeHumidity: String

    var body: some View {
        VStack(alignment: .leading) {
            Text("Temperature: \(temperature)")
            Text("Wind Speed: \(windSpeed)")
            Text("Relative Humidity: \(relativeHumidity)")
        }
        .padding()
        .background(Color(.systemGray6))
        .cornerRadius(10)
        .padding(.bottom, 8)
    }
}



//class WeatherViewModelWrapper: ObservableObject {
//    @Published var state: WeatherUIState = .loading
//    private let viewModel = WeatherViewModel()
//
//    init(lat: String, long: String) {
//        viewModel.getWeather(lat: lat, long: long) { result in
//            DispatchQueue.main.async {
//                switch result {
//                case let .success(data):
//                    self.state = .success(data: data)
//                case let .failure(error):
//                    self.state = .error(message: error.localizedDescription)
//                }
//            }
//        }
//    }
//}

enum WeatherUIState {
    case loading
    case success(data: String)
    case error(message: String)
}




struct WeatherView: View {
    let weather: WeatherResponseIos
    
    var body: some View {
        ScrollView {
            VStack(alignment: .leading, spacing: 4) {
                Group {
                    Text("🌍 Location")
                        .font(.title2.bold())
                    Text("Latitude: \(String(weather.latitude ))")
                    Text("Longitude: \(String(weather.longitude ))")
                }

                Divider().padding(.vertical, 4)

                Group {
                    Text("🌡️ Current Weather")
                        .font(.title2.bold())
                    Text("Temperature: \(String(weather.current.temperature2M )) \(String(weather.currentUnits.temperature2M ))")
                    Text("Wind Speed: \(String(weather.current.windSpeed10M)) \(String(weather.currentUnits.windSpeed10M ))")
                }

                Divider().padding(.vertical, 4)

                Group {
                    
                    VStack(alignment: .leading, spacing: 8) {
                        Text("🕒 Hourly Forecast")
                            .font(.title2.bold())
//                        // Make sure all arrays have the same count
                        ForEach(0..<weather.hourly.temperature2M.count ) { index in
                            DetailItem(
                                temperature: "\(String(weather.hourly.temperature2M[index] )) \(String(weather.hourlyUnits.temperature2M ))",
                                windSpeed: "\(String(weather.hourly.windSpeed10M[index] )) \(String(weather.hourlyUnits.windSpeed10M ))",
                                relativeHumidity: "\(String(weather.hourly.relativeHumidity2M![index] )) \(String(weather.hourlyUnits.relativeHumidity2M ?? ""))"
                            )
                        }
                    }
                }
            }
            .padding()
        }
    }
}
