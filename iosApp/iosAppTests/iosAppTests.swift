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
    
    func testCompanionObjectUsage() {
        XCTAssertEqual(12, KotlinLibraryContainerCompanion().my_SUPER_NICE_CONST)
        let firstContainer = KotlinLibraryContainer(state: "first")
        let secondContainer = KotlinLibraryContainer(state: "second")
        
        XCTAssertFalse(firstContainer === secondContainer)
        
        KotlinLibraryContainerCompanion().sharedVar = "Hello"
        
        XCTAssert(firstContainer.getDecoratedState() == "Hello first")
        XCTAssert(secondContainer.getDecoratedState() == "Hello second")
    }
    
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
    
    func testDslUsage() {
        
        let conf = KotlinLibrary.configuration(init: {
            $0.host = "127.0.0.1"
            $0.port.invoke(init: {
                $0.value = 8080
                $0.isSecure = false
                return KotlinLibraryStdlibUnit()
            })
            return KotlinLibraryStdlibUnit()
        })
        
        XCTAssert(conf.host == "127.0.0.1")
        XCTAssert(conf.port.value == 8080)
        XCTAssert(conf.port.isSecure == false)
    }
}
