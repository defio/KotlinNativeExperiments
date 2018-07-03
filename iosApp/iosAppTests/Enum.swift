//
//  sdf.swift
//  iosAppTests
//
//  Created by Nicola De Fiorenze on 03/07/18.
//  Copyright © 2018 Nicola De Fiorenze. All rights reserved.
//

import XCTest
import KotlinLibrary

class Enum: XCTestCase {
    
    func testUsageOfSimpleEnum() {
        print(KotlinLibrarySimpleEnum.first)
        print(KotlinLibrarySimpleEnum.second)
        print(KotlinLibrarySimpleEnum.third)
    }

    func testUsageOfEnumWithValue() {
        XCTAssert(KotlinLibraryEnumWithValue.one.associatedValue == 1)
        XCTAssert(KotlinLibraryEnumWithValue.two.associatedValue == 2)
    }
    
}
