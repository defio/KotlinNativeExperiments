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
}
