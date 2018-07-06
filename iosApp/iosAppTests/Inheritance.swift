//
//  Inheritance.swift
//  iosAppTests
//
//  Created by Nicola De Fiorenze on 04/07/18.
//  Copyright © 2018 Nicola De Fiorenze. All rights reserved.
//

import XCTest
import KotlinLibrary

class Inheritance: XCTestCase {
    
    func testInheritanceForFinalClass(){
        
        class Attempt: KotlinLibraryCannotInheritFromMe {
            
            override init(parentValue: Int32) {
                super.init(parentValue: parentValue)
            }
        }
        
        let attempt = Attempt(parentValue: 24)
        XCTAssert(attempt.parentValue == 24)
    }
    
    func testInheritanceForOpenClass(){
        
        class Attempt: KotlinLibraryYouCanInheritFromMe {
            
            override init(parentValue: Int32) {
                super.init(parentValue: parentValue)
            }
        }
        
        let attempt = Attempt(parentValue: 24)
        XCTAssert(attempt.parentValue == 24)
    }
    
    
    func testInheritanceForAbstractClass(){
        
        class Attempt: KotlinLibraryAbstractClass {
            var value : String
            
            init(childValue: String, abstractClassValue: Int32) {
                value = childValue
                super.init(abstractClassValue: abstractClassValue)
            }
            
            override func transformValue() -> String {
                return "\(value) : \(abstractClassValue)"
            }
            
        }
        
        let attempt = Attempt(childValue: "value for Child", abstractClassValue: 1)
        XCTAssert(attempt.abstractClassValue == 1)
        XCTAssert(attempt.value == "value for Child")
        XCTAssert("value for Child : 1" == attempt.transformValue())
        XCTAssert(19 == attempt.concreteFunction())
    }
    
    func testInheritanceForInterface(){
        
        class Rect : KotlinLibraryShape {
            let width, height: Float
            
            init(width: Float, height: Float) {
                self.width = width
                self.height = height
            }
            
            var interfaceValue: Int32{
                get {
                    return 456
                }
            }
            
            lazy var interfaceVariable = "Hi I'm a rect with width = \(width) and height = \(height)"
            
            func area() -> Float {
                return width * height
            }
            
            func perim() -> Float {
                return 2 * (width + height)
            }
            
        }
        
        let rect = Rect(width: 2.0, height: 3.0)
        let square = KotlinLibrarySquare(side:2.0)
        
        XCTAssert(rect is KotlinLibraryShape)
        XCTAssert(square is KotlinLibraryShape)
        
        XCTAssert(10.0 == rect.perim())
        XCTAssert(6.0 == rect.area())
        XCTAssert("Hi I'm a rect with width = 2.0 and height = 3.0" == rect.interfaceVariable)
        XCTAssert(456 == rect.interfaceValue, "Failed: rect.interfaceVariable is \(String(describing: rect.interfaceValue))")
        
        XCTAssert(2.0 == square.side)
        XCTAssert(8.0 == square.perim())
        XCTAssert(4.0 == square.area())
        XCTAssert("Hi I'm a square with side = 2.0" == square.interfaceVariable)
        XCTAssert(123 == square.interfaceValue)
    }
    
}
