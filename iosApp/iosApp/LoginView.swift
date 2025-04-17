//
//  LoginView.swift
//  iosApp
//
//  Created by admin on 16/04/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import Foundation

import SwiftUI

struct LoginView: View {
    @State private var email = ""
    @State private var password = ""
    @EnvironmentObject var router: AppRouter

    var body: some View {
        VStack {
            Text("Login")
                .font(.system(size: 24, weight: .bold))
                .frame(maxWidth: .infinity)
                .padding(.top, 40)
                .padding(.bottom, 20)
                .multilineTextAlignment(.center)

            TextField("Email", text: $email)
                .textFieldStyle(.roundedBorder)
                .padding(.horizontal, 10)
                .padding(.vertical, 10)

            SecureField("Password", text: $password)
                .textFieldStyle(.roundedBorder)
                .padding(.horizontal, 10)
                .padding(.vertical, 10)

            Button(action: {
                router.navigate(to: .dashboard)
            }) {
                Text("Login")
                    .font(.system(size: 18, weight: .medium))
                    .frame(maxWidth: .infinity)
                    .padding()
                    .background(Color.blue)
                    .foregroundColor(.white)
                    .cornerRadius(10)
            }
            .padding(.horizontal, 10)
            .padding(.top, 20)

            Spacer()
        }
        .padding()
        .background(Color.white)
    }
}
