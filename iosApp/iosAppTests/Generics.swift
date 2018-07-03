//
//  Generics.swift
//  iosAppTests
//
//  Created by Nicola De Fiorenze on 03/07/18.
//  Copyright © 2018 Nicola De Fiorenze. All rights reserved.
//

import XCTest
import KotlinLibrary

class Generics: XCTestCase {

    func testGenericsBasicUsage() {
        let intBox = KotlinLibraryBox(elemet: 8)
        let stringBox = KotlinLibraryBox(elemet: "Hi")
        
        XCTAssert(intBox.elemet as! Int == 8)
        XCTAssert(stringBox.elemet as! String == "Hi")
        
        intBox.elemet = "World" //<--- NO compilation error!!
        XCTAssert(intBox.elemet as! String == "World")
    }
    
    func testGenericsUsage() {
        let animalBox = KotlinLibraryAnimalBox(elemet: KotlinLibraryDog())
        XCTAssert(animalBox.elemet.speak() == "woof")
        animalBox.elemet = KotlinLibraryCat()
        XCTAssert(animalBox.elemet.speak() == "meow")
 
        //animalBox.elemet = 2 // <--- compilation error
    }
    
    func testGenericsMapUsage() {
        KotlinLibrary.mapIntegersToStrings[0] = "zero"
        KotlinLibrary.mapIntegersToStrings[1] = "one"
        KotlinLibrary.mapIntegersToStrings[2] = 2
        KotlinLibrary.mapIntegersToStrings["three"] = "three"
        
        XCTAssert("zero" == KotlinLibrary.mapIntegersToStrings[0] as! String)
        XCTAssert("one" == KotlinLibrary.mapIntegersToStrings[1] as! String)
        XCTAssert(2 == KotlinLibrary.mapIntegersToStrings[2] as! Int)
        XCTAssert("three" == KotlinLibrary.mapIntegersToStrings["three"] as! String)
    }
    
}


