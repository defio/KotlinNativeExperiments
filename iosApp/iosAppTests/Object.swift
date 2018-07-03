//
//  sdf.swift
//  iosAppTests
//
//  Created by Nicola De Fiorenze on 03/07/18.
//  Copyright © 2018 Nicola De Fiorenze. All rights reserved.
//

import XCTest
import KotlinLibrary

class Object: XCTestCase {
    
    func testKotlinObjectUsage() {
        XCTAssert(KotlinLibraryKotlinObject() === KotlinLibraryKotlinObject())
    }

    func testKotlinObjectUsage_properties() {
        playWithIntProperty()
        playWithNullableAnyProperty()
        playWithNullableStringProperty()
    }

    private func playWithIntProperty(){
        XCTAssert(KotlinLibraryKotlinObject().internalStateInt == KotlinLibraryKotlinObject().initial_VALUE_FOR_INT_PROPERTY)
        KotlinLibraryKotlinObject().internalStateInt = 123
        XCTAssert(KotlinLibraryKotlinObject().internalStateInt == 123)
    }

    private func playWithNullableAnyProperty(){
        XCTAssert((KotlinLibraryKotlinObject().internalStateNullableAny as! String) == KotlinLibraryKotlinObject().initial_VALUE_FOR_NULLABLE_ANY_PROPERTY )
        KotlinLibraryKotlinObject().internalStateNullableAny = 123
        XCTAssert((KotlinLibraryKotlinObject().internalStateNullableAny as! Int) == 123)
        KotlinLibraryKotlinObject().internalStateNullableAny = "Now I'm a string"
        XCTAssert((KotlinLibraryKotlinObject().internalStateNullableAny as! String) == "Now I'm a string")
    }

    private func playWithNullableStringProperty(){
        XCTAssert(KotlinLibraryKotlinObject().internalStateNullableString == (KotlinLibraryKotlinObject().initial_VALUE_FOR_NULLABLE_STRING_PROPERTY as! String?))
        KotlinLibraryKotlinObject().internalStateNullableString = "Now I'm not null"
        XCTAssert(KotlinLibraryKotlinObject().internalStateNullableString! == "Now I'm not null")
    }
    
}
