//
//  BatteryInfo.h
//  BatteryHealth
//
//  Created by Jitendra Meena on 02/12/21.
//  Copyright © 2021 Facebook. All rights reserved.
//

#ifndef BatteryInfo_h
#define BatteryInfo_h

#import <Foundation/Foundation.h>

@interface BatteryInfo : NSObject

- (float) batteryHealth;

- (float) batteryTemperature;

- (float) voltage;

@property (readonly) NSInteger batteryMaximumCapacity;

@property (readonly) NSInteger batteryDesignCapacity;

@property (readonly) NSInteger batteryCycleCount;

@end

#endif /* BatteryInfo_h */
