//
//  iosAppTests.swift
//  iosAppTests
//
//  Created by Nicola De Fiorenze on 19/06/18.
//  Copyright © 2018 Nicola De Fiorenze. All rights reserved.
//

import XCTest
import KotlinLibrary

class iosAppTests: XCTestCase {
    
    func testExample() {
        assert(KotlinLibraryGreeting().greeting() == "Hello, iOS")
    }
    
    func testUsageOfSimpleEnum() {
        print(KotlinLibrarySimpleEnum.first)
        print(KotlinLibrarySimpleEnum.second)
        print(KotlinLibrarySimpleEnum.third)
    }
}
