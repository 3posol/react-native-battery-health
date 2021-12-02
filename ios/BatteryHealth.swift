import Foundation
@objc(BatteryHealth)
class BatteryHealth: NSObject {

    @objc(multiply:withB:withResolver:withRejecter:)
    func multiply(a: Float, b: Float, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        resolve(a*b)
    }
    @objc(getHealth:withRejecter:)
    func getHealth(resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        let instanceOfBatteryInfo = BatteryInfo();
        var response = Dictionary<NSString, Float>()
        response["health"] = instanceOfBatteryInfo.batteryHealth();
        response["temperature"] = instanceOfBatteryInfo.batteryTemperature();
        response["maximumCapacity"] = instanceOfBatteryInfo.batteryTemperature();
        response["designCapacity"] = instanceOfBatteryInfo.batteryTemperature();
        resolve( response )
    }
}
