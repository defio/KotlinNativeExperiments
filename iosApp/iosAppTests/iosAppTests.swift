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
        XCTAssert(KotlinLibraryGreeting().greeting() == "Hello, iOS")
    }
    
    func testUsageOfSimpleEnum() {
        print(KotlinLibrarySimpleEnum.first)
        print(KotlinLibrarySimpleEnum.second)
        print(KotlinLibrarySimpleEnum.third)
    }
    
    func testUsageOfEnumWithValue() {
        XCTAssert(KotlinLibraryEnumWithValue.one.associatedValue == 1)
        XCTAssert(KotlinLibraryEnumWithValue.two.associatedValue == 2)
    }
    
    func testSealedClassUsage() {
        let successMessage = "I'm the winner"
        assertSuccessInstance(selead : KotlinLibrarySealedClassExampleSuccess(successMessage: successMessage), expectedMessage : successMessage)
        assertErrorInstance(selead : KotlinLibrarySealedClassExampleError())
    }
    
    private func assertSuccessInstance(selead : KotlinLibrarySealedClassExample, expectedMessage: String){
        XCTAssert(selead is KotlinLibrarySealedClassExampleSuccess,"Failed: sealed is \(String(describing: selead.self))")
        let success = selead as! KotlinLibrarySealedClassExampleSuccess
        XCTAssert(success.successMessage == expectedMessage,"Failed: message is '\(String(describing: success.successMessage))' the expected is '\(String(describing: expectedMessage))' ")
    }
    
    private func assertErrorInstance(selead : KotlinLibrarySealedClassExample){
        XCTAssert(selead is KotlinLibrarySealedClassExampleError)
    }
    
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
