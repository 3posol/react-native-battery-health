//
//  BatteryInfo.m
//  BatteryHealth
//
//  Created by Jitendra Meena on 02/12/21.
//  Copyright © 2021 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "BatteryInfo.h"

@interface BatteryInfo()
{
    NSDictionary *latestPowerDictionary;
}
@end

@implementation BatteryInfo


- (float) batteryHealth
{
    float rawHealth = [latestPowerDictionary[@"AppleRawMaxCapacity"] floatValue] / [latestPowerDictionary[@"DesignCapacity"] floatValue];
    return MAX(MIN(rawHealth, 1.0), 0.0);
};

- (float) batteryTemperature
{
    return [latestPowerDictionary[@"Temperature"] floatValue] / 100.0;
}

- (NSInteger) batteryDesignCapacity
{
    return [latestPowerDictionary[@"DesignCapacity"] integerValue];
}

- (NSInteger) batteryMaximumCapacity
{
    return [latestPowerDictionary[@"AppleRawMaxCapacity"] integerValue];
}

- (float) voltage
{
    return [latestPowerDictionary[@"Voltage"] floatValue] / 1000.0;
}

- (NSInteger) batteryCycleCount
{
    return [latestPowerDictionary[@"CycleCount"] integerValue];
}

@end
