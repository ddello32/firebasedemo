//
//  MessageRepository.swift
//  firestoredemo
//
//  Created by Daniel Oliveira on 7/31/19.
//  Copyright © 2019 Daniel Oliveira. All rights reserved.
//
import Firebase
import Foundation

class MessageRepository {
    var db : Firestore!
    
    init() {
        let settings = FirestoreSettings()
        Firestore.firestore().settings = settings
        db = Firestore.firestore()
    }
    
    func saveMessage(message: Message) {
        db.collection("messages").addDocument(data: ["userName": message.userName, "message": message.message, "timestamp": message.timestamp])
    }
    
    func listenForMessages(callback: @escaping ([Message]?)->()) {
        db.collection("messages").order(by: "timestamp", descending: false).addSnapshotListener { (snapshot, error) in
            if(error == nil) {
                callback(snapshot?.documents.map {doc in
                    let msg = doc.data()
                    return Message(userName: msg["userName"] as! String, message: msg["message"] as! String, timestamp: (msg["timestamp"] as! Timestamp).dateValue())
                })
            } else {
                callback([])
            }
        }
    }
}
