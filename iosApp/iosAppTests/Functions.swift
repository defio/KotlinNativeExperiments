//
//  sdf.swift
//  iosAppTests
//
//  Created by Nicola De Fiorenze on 03/07/18.
//  Copyright © 2018 Nicola De Fiorenze. All rights reserved.
//

import XCTest
import KotlinLibrary

class Functions: XCTestCase {
    
    func testTopLevelFunctionUsage() {
        XCTAssert("I am a top level" == KotlinLibrary.topLevelFunction())
    }

    func testExtensionFunctionUsage() {
        XCTAssert(4 == KotlinLibrary.extensionFuction(2))
    }

    func testHigherOrderFunctionWithParameterUsage() {
        XCTAssert(5 == KotlinLibrary.higherOrderFunctionBoth(a: 2, b: 3, f: {
            NSNumber(value: ($0.doubleValue + $1.doubleValue))
        }))
    }

    func testHigherOrderFunctionWithReturnUsage() {
        XCTAssert(5 == KotlinLibrary.higherOrderFunctionWithReturn(functionType: KotlinLibraryFunctionType.sum)(2, 3))

        let higherOrderFunctionWithReturn = KotlinLibrary.higherOrderFunctionWithReturn(functionType: KotlinLibraryFunctionType.multiply)
        XCTAssert(6 == higherOrderFunctionWithReturn(2, 3))
    }

    func testHigherOrderFunctionBothUsage() {
        XCTAssert(25 == KotlinLibrary.higherOrderFunctionBoth(a: 2, b: 3, f: {
            NSNumber(value: pow(($0.doubleValue + $1.doubleValue),2))
        }))
    }
    
}
