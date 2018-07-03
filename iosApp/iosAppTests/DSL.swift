//
//  sdf.swift
//  iosAppTests
//
//  Created by Nicola De Fiorenze on 03/07/18.
//  Copyright © 2018 Nicola De Fiorenze. All rights reserved.
//

import XCTest
import KotlinLibrary

class DSL: XCTestCase {
    
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
