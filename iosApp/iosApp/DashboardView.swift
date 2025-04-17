//
//  DashboardView.swift
//  iosApp
//
//  Created by admin on 16/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct DashboardView: View {
    @EnvironmentObject var router: AppRouter
    let locations = ["New York", "London", "Tokyo", "Sydney"]
    let latList = ["40.730610", "51.509865", "35.6895", "-33.865143"]
    let longList = ["-73.935242", "-0.118092", "139.69171", "151.209900"]

    var body: some View {
        VStack {
            Text("Dashboard")
                .font(.system(size: 18, weight: .bold))
                .frame(maxWidth: .infinity)
                .padding(.top, 40)
                .padding(.bottom, 20)
                .multilineTextAlignment(.center)

            LocationListView(
                locations: locations,
                latitudes: latList,
                longitudes: longList
            )
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity)
        .background(Color.white)
    }
}

struct LocationListView: View {
    let locations: [String]
    let latitudes: [String]
    let longitudes: [String]
    
    @EnvironmentObject var router: AppRouter

    var body: some View {
        List(locations.indices, id: \.self) { index in
            LocationItemView(location: locations[index]) {
                print("Tapped: \(locations[index])")
                router.navigate(
                    to: .detail(
                        name: locations[index],
                        lat: latitudes[index],
                        long: longitudes[index]
                    )
                )
            }
        }
        .listStyle(.plain)
    }
}


struct LocationItemView: View {
    let location: String
    let onTap: () -> Void

    var body: some View {
        CardView {
            Text(location)
                .font(.body)
                .padding()
        }
        .onTapGesture {
            onTap()
        }
        .padding(.vertical, 4)
    }
}


struct CardView<Content: View>: View {
    let content: () -> Content

    var body: some View {
        RoundedRectangle(cornerRadius: 10)
            .fill(Color(.systemGray6))
            .overlay(
                content()
                    .frame(maxWidth: .infinity, alignment: .leading)
            )
    }
}
