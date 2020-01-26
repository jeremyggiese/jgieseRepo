//
//  PaymentViewController.swift
//  Finances
//
//  Created by Jeremy Giese on 8/7/19.
//  Copyright © 2019 Jeremy Giese. All rights reserved.
//

import UIKit
import os.log

class PaymentViewController: UIViewController, UITextFieldDelegate, UINavigationControllerDelegate {
    
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var dateText: UITextField!
    @IBOutlet weak var rentText: UITextField!
    @IBOutlet weak var waterText: UITextField!
    @IBOutlet weak var electricText: UITextField!
    @IBOutlet weak var internetText: UITextField!
    @IBOutlet weak var carText: UITextField!
    @IBOutlet weak var payText: UITextField!
    @IBOutlet weak var sumLabel: UILabel!
    @IBOutlet weak var gasText: UITextField!
    @IBOutlet weak var saveText: UITextField!
    
    
    @IBOutlet weak var cancelButton: UIBarButtonItem!
    @IBOutlet weak var saveButton: UIBarButtonItem!
    
    let datePicker = UIDatePicker()
    var payment: Payment?
    let numberToolbar: UIToolbar = UIToolbar()
    var activeTextField: UITextField!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        showDatePicker()
        addDoneButtonOnKeyboard()
        addNextButtonOnKeyboard()
        registerForKeyboardNotifications()
        dateText.delegate = self
        rentText.delegate = self
        waterText.delegate = self
        electricText.delegate = self
        internetText.delegate = self
        carText.delegate = self
        payText.delegate = self
        gasText.delegate = self
        saveText.delegate = self
        activeTextField = dateText
        if let payment = payment {
            datePicker.date = payment.date
            doneDatePicker()
            rentText.text = String(payment.rent)
            waterText.text = String(payment.water)
            electricText.text = String(payment.electric)
            internetText.text = String(payment.internet)
            carText.text = String(payment.car)
            payText.text = String(payment.pay)
            sumLabel.text = payment.total
            gasText.text = String(payment.gas)
            saveText.text = String(payment.save)
        }
       
        
        updateSaveButtonState()
        // Do any additional setup after loading the view.
    }
   
   
    //MARK: UITextFieldDelegate
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        let nextTag = textField.tag + 1
        // Try to find next responder
        let nextResponder = textField.superview?.viewWithTag(nextTag) as UIResponder?
        
        if nextResponder != nil {
            // Found next responder, so set it
            nextResponder?.becomeFirstResponder()
        } else {
            // Not found, so remove keyboard
            textField.resignFirstResponder()
        }
        
        return false
    }
    func textFieldDidEndEditing(_ textField: UITextField){
        let sum = getSum()
        sumLabel.text = "Final Total: \(sum)"
        updateSaveButtonState()
        navigationItem.title = dateText.text ?? ""
    }
    func textFieldDidBeginEditing(_ textField: UITextField) {
        activeTextField = textField
        saveButton.isEnabled = false
    }
    
    
    //MARK: Navigation
    // This method lets you configure a view controller before it's presented.
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
       
        let date = datePicker.date
        let rent = Double(rentText.text ?? "") ?? 0.0
        let water = Double(waterText.text ?? "") ?? 0.0
        let electric = Double(electricText.text ?? "") ?? 0.0
        let internet = Double(internetText.text ?? "") ?? 0.0
        let car = Double(carText.text ?? "") ?? 0.0
        let pay = Double(payText.text ?? "") ?? 0.0
        let total = sumLabel.text ?? ""
        let gas = Double(gasText.text ?? "") ?? 0.0
        let save = Double(saveText.text ?? "") ?? 0.0
        super.prepare(for: segue, sender: sender)
        // Configure the destination view controller only when the save button is pressed.
        guard let button = sender as? UIBarButtonItem, button === saveButton else {
            os_log("The save button was not pressed, cancelling", log: OSLog.default, type: .debug)
            return
        }
        
        
        payment = Payment(date: date, rent: rent, water: water, electric: electric, internet: internet, car: car, pay: pay, total: total, gas: gas, save: save)
        
    }
    @IBAction func cancel(_ sender: UIBarButtonItem) {
        // Depending on style of presentation (modal or push presentation), this view controller needs to be dismissed in two different ways.
        let isPresentingInAddPaymentMode = presentingViewController is UINavigationController
        
        if isPresentingInAddPaymentMode {
            dismiss(animated: true, completion: nil)
        }
        else if let owningNavigationController = navigationController{
            owningNavigationController.popViewController(animated: true)
        }
        else {
            fatalError("The PaymentViewController is not inside a navigation controller.")
        }
    }
    
    func showDatePicker(){
        //Formate Date
        datePicker.datePickerMode = .date
        datePicker.setValue(UIColor.white, forKey: "textColor")
        datePicker.backgroundColor = UIColor.black.withAlphaComponent(0.8)
        
        //ToolBar
        let toolbar = UIToolbar();
        toolbar.sizeToFit()
        toolbar.barStyle = .black
        let doneButton = UIBarButtonItem(title: "Next", style: .plain, target: self, action: #selector(doneDatePicker));
        let spaceButton = UIBarButtonItem(barButtonSystemItem: UIBarButtonItem.SystemItem.flexibleSpace, target: nil, action: nil)
        let cancelButton = UIBarButtonItem(title: "Cancel", style: .plain, target: self, action: #selector(cancelDatePicker));
        
        toolbar.setItems([cancelButton,spaceButton,doneButton], animated: false)
        
        dateText.inputAccessoryView = toolbar
        dateText.inputView = datePicker
        
    }
    @objc func doneDatePicker(){
        
        let formatter = DateFormatter()
        formatter.dateFormat = "MM/dd/yyyy"
        dateText.text = formatter.string(from: datePicker.date)
        textFieldShouldReturn(activeTextField)
    }
    
    @objc func cancelDatePicker(){
        self.view.endEditing(true)
    }
    
    func addDoneButtonOnKeyboard(){
        let doneToolbar: UIToolbar = UIToolbar(frame: CGRect.init(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 50))
        doneToolbar.barStyle = .black
        
        let cancelButton = UIBarButtonItem(title: "Cancel", style: .plain, target: self, action: #selector(cancelButtonAction));
        let flexSpace = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
        let done: UIBarButtonItem = UIBarButtonItem(title: "Done", style: .done, target: self, action: #selector(self.doneButtonAction))
        
        let items = [cancelButton, flexSpace, done]
        doneToolbar.items = items
        doneToolbar.sizeToFit()
        
        payText.inputAccessoryView = doneToolbar
    }
    
    func addNextButtonOnKeyboard(){
        let nextToolbar: UIToolbar = UIToolbar(frame: CGRect.init(x: 0, y: 0, width: UIScreen.main.bounds.width, height: 50))
        nextToolbar.barStyle = .black
        
        let cancelButton = UIBarButtonItem(title: "Cancel", style: .plain, target: self, action: #selector(cancelButtonAction));
        let flexSpace = UIBarButtonItem(barButtonSystemItem: .flexibleSpace, target: nil, action: nil)
        let next: UIBarButtonItem = UIBarButtonItem(title: "Next", style: .done, target: self, action: #selector(self.doneButtonAction))
        
        let items = [cancelButton, flexSpace, next]
        nextToolbar.items = items
        nextToolbar.sizeToFit()
        
        rentText.inputAccessoryView = nextToolbar
        waterText.inputAccessoryView = nextToolbar
        electricText.inputAccessoryView = nextToolbar
        internetText.inputAccessoryView = nextToolbar
        carText.inputAccessoryView = nextToolbar
        gasText.inputAccessoryView = nextToolbar
        saveText.inputAccessoryView = nextToolbar
       
    }
    
    @objc func doneButtonAction(){
        textFieldShouldReturn(activeTextField)
        
        
    }
    @objc func cancelButtonAction(){
        self.view.endEditing(true)
        
    }
        
    
    
    

    //MARK: Private Methods
    private func updateSaveButtonState() {
        // Disable the Save button if the text field is empty.
        let text = dateText.text ?? ""
        saveButton.isEnabled = !text.isEmpty
    }
    private func getSum()->Double{
        
        let rent = Double(rentText.text ?? "") ?? 0.0
        let water = Double(waterText.text ?? "") ?? 0.0
        let electric = Double(electricText.text ?? "") ?? 0.0
        let internet = Double(internetText.text ?? "") ?? 0.0
        let car = Double(carText.text ?? "") ?? 0.0
        let pay = Double(payText.text ?? "") ?? 0.0
        let gas = Double(gasText.text ?? "") ?? 0.0
        let save = Double(saveText.text ?? "") ?? 0.0
        var neg = 0.0
        neg+=electric
        neg+=internet
        neg+=water
        neg+=rent
        neg+=car
        neg+=gas
        neg+=save
        return pay-neg
        
    }
    func registerForKeyboardNotifications() {
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(keyboardWillShow(notification:)),
                                               name: UIResponder.keyboardWillShowNotification,
                                               object: nil)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(keyboardWillHide(notification:)),
                                               name: UIResponder.keyboardWillHideNotification,
                                               object: nil)
    }
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self)
    }
    
    @objc func keyboardWillShow(notification: NSNotification) {
        let userInfo: NSDictionary = notification.userInfo! as NSDictionary
        let keyboardInfo = userInfo[UIResponder.keyboardFrameBeginUserInfoKey] as! NSValue
        let keyboardSize = keyboardInfo.cgRectValue.size
        let contentInsets = UIEdgeInsets(top: 0, left: 0, bottom: keyboardSize.height, right: 0)
        scrollView.contentInset = contentInsets
        scrollView.scrollIndicatorInsets = contentInsets
    }
    
    @objc func keyboardWillHide(notification: NSNotification) {
        scrollView.contentInset = .zero
        scrollView.scrollIndicatorInsets = .zero
    }
    deinit {
        NotificationCenter.default.removeObserver(self)
    }
}

