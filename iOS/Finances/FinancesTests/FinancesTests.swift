//
//  FinancesTests.swift
//  FinancesTests
//
//  Created by Jeremy Giese on 8/7/19.
//  Copyright © 2019 Jeremy Giese. All rights reserved.
//

import XCTest
@testable import Finances

class FinancesTests: XCTestCase {

    func testPaymentInitializationSucceeds() {
        // Zero rating
        let zeroRentPayment = Payment.init(date: "Test", rent: 0.0, water: 1.0, electric: 2.0, internet: 3.3, car: 1.1, pay: 100.0, total: "Test")
        XCTAssertNotNil(zeroRentPayment)
        
        // Highest positive rating
        
    }
    
}
