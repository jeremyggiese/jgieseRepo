//
//  ViewController.swift
//  KinematicsSolver
//
//  Created by Jeremy Giese on 10/1/19.
//  Copyright © 2019 Jeremy Giese. All rights reserved.
//

import UIKit

class ViewController: UIViewController, UITextFieldDelegate {

    @IBOutlet weak var xInitialLabel: UILabel!
    @IBOutlet weak var xFinalLabel: UILabel!
    
    @IBOutlet weak var xInitialTextField: UITextField!
    @IBOutlet weak var xFinalTextField: UITextField!
    @IBOutlet weak var velocityInitialTextField: UITextField!
    
    @IBOutlet weak var accelerationTextField: UITextField!
    @IBOutlet weak var velocityFinalTextField: UITextField!
    
    @IBOutlet weak var kineticEnergyTextField: UITextField!
    
    @IBOutlet weak var angleTextField: UITextField!
    @IBOutlet weak var forceTextField: UITextField!
    @IBOutlet weak var massTextField: UITextField!
    @IBOutlet weak var timeTextField: UITextField!
    @IBOutlet weak var solveButton: UIButton!
    @IBOutlet weak var resetButton: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        xInitialTextField.delegate = self
        xFinalTextField.delegate = self
        velocityFinalTextField.delegate = self
        velocityInitialTextField.delegate = self
        accelerationTextField.delegate = self
        forceTextField.delegate = self
        
        massTextField.delegate = self
        timeTextField.delegate = self
        angleTextField.delegate = self
        // Do any additional setup after loading the view.
    }
    
    @IBAction func solvePressed(_ sender: Any) {
        var xInitial = Double(xInitialTextField.text ?? "") ?? 0.0
        var xFinal = Double(xFinalTextField.text ?? "") ?? 0.0
        var vInitial = Double(velocityInitialTextField.text ?? "") ?? 0.0
        var vFinal = Double(velocityFinalTextField.text ?? "") ?? 0.0
        var acceleration = Double(accelerationTextField.text ?? "") ?? 0.0
        var timeValue  = Double(timeTextField.text ?? "") ?? 0.0
        var mass = Double(massTextField.text ?? "") ?? 0.0
        var force = Double(forceTextField.text ?? "") ?? 0.0
        var angle = Double(angleTextField.text ?? "") ?? 0.0
        var kineticEnergy = Double(kineticEnergyTextField.text ?? "") ?? 0.0
        if(vFinal == 0.0 && acceleration > 0.0 && timeValue > 0.0 && xInitial == 0.0 && xFinal == 0.0){
            vFinal = vInitial + (acceleration * timeValue)
            velocityFinalTextField.text = String(vFinal)
        }else if(vFinal == 0.0 && acceleration > 0.0 && timeValue > 0.0 && xFinal > 0.0){
            vFinal = ((vInitial*vInitial)+2*acceleration*(xFinal-xInitial)).squareRoot()
            velocityFinalTextField.text = String(vFinal)
        }else if(vFinal > 0.0 && vInitial > 0.0 && xFinal == 0.0){
            xFinal = (1/2)*(vFinal+vInitial)*timeValue
            xFinalTextField.text = String(xFinal)
        }else if(xFinal == 0.0 && acceleration > 0.0 && timeValue > 0.0){
            xFinal = xInitial + (vInitial*timeValue)+(1/2*acceleration*timeValue)
            xFinalTextField.text = String(xFinal)
        }else if(xFinal>0 && vInitial == 0.0 && vFinal == 0.0){
            vFinal = 2*(xFinal - xInitial)/timeValue
            velocityFinalTextField.text = String(vFinal)
        }else if(timeValue == 0.0 && xFinal > 0.0){
            timeValue = (2*(xFinal-xInitial))/(vFinal+vInitial)
            timeTextField.text = String(timeValue)
        }else if(timeValue == 0.0 && vFinal > 0.0 && acceleration > 0.0){
            timeValue = (vFinal-vInitial)/acceleration
            timeTextField.text = String(timeValue)
        }else if(acceleration == 0.0 && vFinal > 0.0){
            acceleration = (vFinal-vInitial/timeValue)
            accelerationTextField.text = String(acceleration)
        }else if(acceleration == 0.0 && vFinal > 0.0 && timeValue > 0.0 && (xInitial>0 || xFinal>0)){
            acceleration = ((vFinal*vFinal)-(vInitial*vInitial))/(2*(xFinal-xInitial))
            accelerationTextField.text = String(acceleration)
        }else if(force > 0.0 && mass == 0.0 && acceleration > 0.0){
            mass = force/acceleration
            massTextField.text = String(mass)
        }else if(mass > 0.0 && acceleration != 0.0 && force == 0.0){
            force = mass*acceleration
            forceTextField.text = String(force)
        }else if(force > 0.0 && (xFinal > 0.0 || xInitial > 0.0)){
            kineticEnergy = Double(abs(force*(xFinal-xInitial)))
            kineticEnergyTextField.text = String(kineticEnergy)
        }else if(force > 0.0 && xFinal == 0.0 && xInitial == 0.0 && angle != 0.0){
            xInitialLabel.text = "F*cos(𝞡)"
            xFinalLabel.text = "F*sin(𝞡)"
            xInitial = force * cos(angle * Double.pi / 180)
            xFinal = force * sin(angle * Double.pi / 180)
            xFinalTextField.text = String(xFinal)
            xInitialTextField.text = String(xInitial)
        }
        
    }
    @IBAction func resetButtonPressed(_ sender: Any) {
        xInitialTextField.text = ""
        xFinalTextField.text = ""
        velocityInitialTextField.text = ""
        velocityFinalTextField.text = ""
        accelerationTextField.text = ""
        timeTextField.text = ""
        massTextField.text = ""
        forceTextField.text = ""
        angleTextField.text = ""
        kineticEnergyTextField.text = ""
        xFinalLabel.text = "x𝚏:"
        xInitialLabel.text = "x𝚒:"
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
        
        
        
       
    }
    func textFieldDidBeginEditing(_ textField: UITextField) {
       
    }
    
    
   
}

