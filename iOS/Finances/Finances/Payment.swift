//
//  Payment.swift
//  Finances
//
//  Created by Jeremy Giese on 8/9/19.
//  Copyright © 2019 Jeremy Giese. All rights reserved.
//

import UIKit
import os.log


class Payment: NSObject, NSCoding, Codable{
    //MARK: Properties
    var date: Date
    var rent: Double
    var water: Double
    var electric: Double
    var internet: Double
    var car: Double
    var pay: Double
    var total: String
    var gas: Double
    var save: Double
    //MARK: Archiving Paths
    
    static let DocumentsDirectory = FileManager().urls(for: .documentDirectory, in: .userDomainMask).first!
    static let ArchiveURL = DocumentsDirectory.appendingPathComponent("payments")
    //MARK: Types
    
    struct PropertyKey: Codable{
        static let date = "date"
        static let rent = "rent"
        static let water = "water"
        static let electric = "electric"
        static let internet = "internet"
        static let car = "car"
        static let pay = "pay"
        static let total = "total"
        static let gas = "gas"
        static let save = "save"
    }
    //MARK: Initialization
    
    init?(date: Date, rent: Double?, water: Double?, electric: Double?, internet: Double?, car: Double?, pay: Double?, total: String, gas: Double?, save: Double?) {
        self.date = date
        self.rent = rent ?? 0
        self.water = water ?? 0
        self.electric = electric ?? 0
        self.internet = internet ?? 0
        self.car = car ?? 0
        self.pay = pay ?? 0
        self.total = total
        self.gas = gas ?? 0
        self.save = save ?? 0
    }
    
    //MARK: NSCoding
    func encode(with aCoder: NSCoder) {
        aCoder.encode(date, forKey: PropertyKey.date)
        aCoder.encode(rent, forKey: PropertyKey.rent)
        aCoder.encode(water, forKey: PropertyKey.water)
        aCoder.encode(electric, forKey: PropertyKey.electric)
        aCoder.encode(internet, forKey: PropertyKey.internet)
        aCoder.encode(car, forKey: PropertyKey.car)
        aCoder.encode(pay, forKey: PropertyKey.pay)
        aCoder.encode(total, forKey: PropertyKey.total)
        aCoder.encode(gas, forKey: PropertyKey.gas)
        aCoder.encode(save, forKey: PropertyKey.save)
    }
    
    required convenience init?(coder aDecoder: NSCoder) {
        // The date is required. If we cannot decode a date string, the initializer should fail.
        guard let date = aDecoder.decodeObject(forKey: PropertyKey.date) as? Date else {
            os_log("Unable to decode the date for a Payment object.", log: OSLog.default, type: .debug)
            return nil
        }
        
        let rent = aDecoder.decodeDouble(forKey: PropertyKey.rent)
        let water = aDecoder.decodeDouble(forKey: PropertyKey.water)
        let electric = aDecoder.decodeDouble(forKey: PropertyKey.electric)
        let internet = aDecoder.decodeDouble(forKey: PropertyKey.internet)
        let car = aDecoder.decodeDouble(forKey: PropertyKey.car)
        let pay = aDecoder.decodeDouble(forKey: PropertyKey.pay)
        let gas = aDecoder.decodeDouble(forKey: PropertyKey.gas)
        let save = aDecoder.decodeDouble(forKey: PropertyKey.save)
        guard let total = aDecoder.decodeObject(forKey: PropertyKey.total) as? String else {
            return nil
        }
        // Must call designated initializer.
        self.init(date: date, rent: rent, water: water, electric: electric, internet: internet, car: car, pay: pay, total: total, gas: gas, save: save)
    }
}
