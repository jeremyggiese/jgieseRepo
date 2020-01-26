//
//  FinanceTableViewController.swift
//  Finances
//
//  Created by Jeremy Giese on 8/9/19.
//  Copyright © 2019 Jeremy Giese. All rights reserved.
//

import UIKit
import os.log

class FinanceTableViewController: UITableViewController {
    var payments = [Payment]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationItem.leftBarButtonItem = editButtonItem
        if let savedPayments = loadPayments() {
            payments += savedPayments
        }
        savePayments()
        
        }
        
        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false

        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
    

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return payments.count
    }
    //MARK: Actions
    @IBAction func unwindToPaymentList(sender: UIStoryboardSegue) {
        if let sourceViewController = sender.source as? PaymentViewController, let payment = sourceViewController.payment {
            
            if let selectedIndexPath = tableView.indexPathForSelectedRow {
               
                payments[selectedIndexPath.row] = payment
                tableView.reloadRows(at: [selectedIndexPath], with: .none)
            }
            else {
                
                
                let newIndexPath = IndexPath(row: payments.count, section: 0)
                
                payments+=[payment]
                tableView.insertRows(at: [newIndexPath], with: .automatic)
            }
            
            savePayments()
        }
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cellIdentifier = "PaymentTableViewCell"
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? PaymentTableViewCell  else {
            fatalError("The dequeued cell is not an instance of PaymentTableViewCell.")
        }
        let payment = payments[indexPath.row]
        let formatter = DateFormatter()
        formatter.dateFormat = "MM/dd/yyyy"
        cell.dateLabel.text = formatter.string(from: payment.date)
        let test = payment.total.split(separator: ":")
        if(payment.total.contains("-")){
            cell.totalLabel.textColor = UIColor.red
        }
        cell.totalLabel.text = "Total \(String(test[1]))"
        // Table view cells are reused and should be dequeued using a cell identifier.
        
        // Configure the cell...
        
        
        return cell
    }

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    
 // Override to support editing the table view.
 override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCell.EditingStyle, forRowAt indexPath: IndexPath) {
 if editingStyle == .delete {
 // Delete the row from the data source
 payments.remove(at: indexPath.row)
 savePayments()
 tableView.deleteRows(at: [indexPath], with: .fade)
 } else if editingStyle == .insert {
 // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
 }
 }

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        super.prepare(for: segue, sender: sender)
        
        switch(segue.identifier ?? "") {
            
        case "AddItem":
            os_log("Adding a new Payment Log.", log: OSLog.default, type: .debug)
            
        case "ShowDetail":
            guard let paymentDetailViewController = segue.destination as? PaymentViewController else {
                fatalError("Unexpected destination: \(segue.destination)")
            }
            
            guard let selectedPaymentCell = sender as? PaymentTableViewCell else {
                fatalError("Unexpected sender: \(String(describing: sender))")
            }
            
            guard let indexPath = tableView.indexPath(for: selectedPaymentCell) else {
                fatalError("The selected cell is not being displayed by the table")
            }
            
            let selectedPayment = payments[indexPath.row]
            paymentDetailViewController.payment = selectedPayment
            
        default:
            fatalError("Unexpected Segue Identifier; \(String(describing: segue.identifier))")
        }
    }
    //MARK: Private Methods
    private func savePayments() {
        let encoder = JSONEncoder()
        if let encoded = try? encoder.encode(payments){
                let defaults = UserDefaults.standard
                defaults.set(encoded, forKey: "Payment")
        }
            
    }
    
    private func loadPayments()->[Payment]?{
        let defaults = UserDefaults.standard
        if let payment = defaults.object(forKey: "Payment") as? Data {
            let decoder = JSONDecoder()
            if let payments = try? decoder.decode([Payment].self, from: payment) {
                return payments
            }
    }
        return nil
    }
    
  
}
