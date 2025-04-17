import SwiftUI
import shared
import Combine
import KMPNativeCoroutinesRxSwift
import KMPNativeCoroutinesCore
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCombine

struct ContentView: View {
	let greet = Greeting().greet()
    
    
//    var body: some View {
//        ListView(phrases: viewModel.greetings)
//            .task { await self.viewModel.startObserving() }
//    }
    
    var body: some View {
            VStack {
                MainContent()
//                Text(weatherText.isEmpty ? "Loading..." : weatherText)
//                    .padding()
//                Button("Fetch Weather") {
//                    apiCall(weatherRespo: WeatherRepositoryImpl(), text: $weatherText)
//                }
            }
        }
}




struct MainContent: View {
    @StateObject private var router = AppRouter()

        var body: some View {
            NavigationStack(path: $router.path) {
                LoginView()
                    .navigationDestination(for: Screen.self) { screen in
                        switch screen {
                        case .dashboard:
                            DashboardView()
                        case .detail(let name, let lat, let long):
                            DetailView(location: name, lat: lat, long: long)
                        case .login:
                            LoginView()
                        }
                    }
            }
            .environmentObject(router)
        }
}


enum Screen: Hashable {
    case login
    case dashboard
    case detail(name: String, lat: String, long: String)
}



class AppRouter: ObservableObject {
    @Published var path = NavigationPath()

    func navigate(to screen: Screen) {
        path.append(screen)
    }

    func reset() {
        path = NavigationPath()
    }
}
