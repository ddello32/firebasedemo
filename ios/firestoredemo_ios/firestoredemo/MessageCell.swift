//
//  MessageCell.swift
//  firestoredemo
//
//  Created by Daniel Oliveira on 7/29/19.
//  Copyright © 2019 Daniel Oliveira. All rights reserved.
//

import UIKit

class MessageCell: UITableViewCell {
    let dateFormatter = DateFormatter()
    @IBOutlet var messageBody: UILabel!
    @IBOutlet var senderUsername: UILabel!
    @IBOutlet var date: UILabel!
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        dateFormatter.dateFormat = "hh:mm:ss"
    }
    
    func bind(message: Message) {
        senderUsername.text = message.userName
        messageBody.text = message.message
        date.text = dateFormatter.string(from: message.timestamp)
    }
    
}
