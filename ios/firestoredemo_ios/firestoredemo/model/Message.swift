//
//  Message.swift
//  firestoredemo
//
//  Created by Daniel Oliveira on 7/29/19.
//  Copyright © 2019 Daniel Oliveira. All rights reserved.
//

import Foundation

class Message {
    var id: String?
    var userName: String
    var message: String
    var timestamp: Date
    
    init(id: String? = nil, userName: String, message: String, timestamp: Date) {
        self.id = id
        self.userName = userName
        self.message = message
        self.timestamp = timestamp
    }
}
