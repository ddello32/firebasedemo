//
//  ViewController.swift
//  firestoredemo
//
//  Created by Daniel Oliveira on 7/29/19.
//  Copyright © 2019 Daniel Oliveira. All rights reserved.
//
import UIKit
import FirebaseCore
import FirebaseFirestore


class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    @IBOutlet var tableView: UITableView!
    @IBOutlet var messageInputView: UITextField!
    
    var repository : MessageRepository!
    var messages : [Message] = [Message]()

    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.register(UINib(nibName: "MessageView", bundle: nil), forCellReuseIdentifier: "MessageCell")
        repository = MessageRepository()
        listenForMessages()
    }
    
    @IBAction func onMessageSendClicked() {
        repository.saveMessage(message: Message(userName: "iOS simulator", message: messageInputView.text!, timestamp: Date()))
        messageInputView.text = ""
    }
    
    func listenForMessages() {
        repository.listenForMessages(callback: { (retrievedMsgs) in
            self.messages.removeAll()
            if(retrievedMsgs != nil) {
                self.messages.append(contentsOf: retrievedMsgs!)
            }
            self.tableView.reloadData()
        })
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return messages.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        // Table view cells are reused and should be dequeued using a cell identifier.
        let cellIdentifier = "MessageCell"
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? MessageCell  else {
            fatalError("The dequeued cell is not an instance of MessageCell.")
        }
        cell.bind(message: messages[indexPath.row])
        return cell
    }
    
}

