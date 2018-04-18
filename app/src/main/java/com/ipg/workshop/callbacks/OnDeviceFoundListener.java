package com.ipg.workshop.callbacks;

import android.bluetooth.BluetoothDevice;

public interface OnDeviceFoundListener {
    void onDeviceFound(BluetoothDevice device);
}
