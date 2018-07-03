//
//  sdf.swift
//  iosAppTests
//
//  Created by Nicola De Fiorenze on 03/07/18.
//  Copyright © 2018 Nicola De Fiorenze. All rights reserved.
//

import XCTest
import KotlinLibrary

class CompanionObject: XCTestCase {
    
    func testCompanionObjectUsage() {
        XCTAssertEqual(12, KotlinLibraryContainerCompanion().my_SUPER_NICE_CONST)
        let firstContainer = KotlinLibraryContainer(state: "first")
        let secondContainer = KotlinLibraryContainer(state: "second")

        XCTAssertFalse(firstContainer === secondContainer)

        KotlinLibraryContainerCompanion().sharedVar = "Hello"

        XCTAssert(firstContainer.getDecoratedState() == "Hello first")
        XCTAssert(secondContainer.getDecoratedState() == "Hello second")
    }

}
