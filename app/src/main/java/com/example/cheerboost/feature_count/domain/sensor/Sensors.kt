package com.example.cheerboost.feature_count.domain.sensor

import android.content.Context
import android.hardware.Sensor

class GravitySensor(
    context: Context
) : AndroidSensor(
    context = context,
    sensorType = Sensor.TYPE_GRAVITY
)

class LinearAccelerationSensor(
    context: Context
) : AndroidSensor(
    context = context,
    sensorType = Sensor.TYPE_LINEAR_ACCELERATION
)
