package com.ipg.workshop;

import android.bluetooth.BluetoothDevice;
import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ipg.workshop.databinding.ActivityMainBinding;

import info.plux.pluxapi.Constants;
import info.plux.pluxapi.bitalino.BITalinoCommunication;
import info.plux.pluxapi.bitalino.BITalinoException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

    public static final String EXTRA_DEVICE = "extra_device";
    public static final String FRAME = "frame";

    private BluetoothDevice device;
    private BITalinoCommunication biTalinoCommunication;

    private Handler handler;
    private Constants.States currentState = Constants.States.DISCONNECTED;

    private boolean isUpdateReceiverRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    /*@Override
    public void onUpdateReceiver(Constants.States states) {
        binding.stateTextView.setText(states.name());

        switch (states){
            case NO_CONNECTION:
                binding.startButton.setEnabled(false);
                binding.stopButton.setEnabled(false);
                binding.disconnectButton.setEnabled(false);
                break;
            case CONNECTING:
                binding.layoutLoadingId.getRoot().setVisibility(View.VISIBLE);
                break;
            case CONNECTED:
                binding.startButton.setEnabled(true);
                binding.stopButton.setEnabled(false);
                binding.connectButton.setEnabled(false);
                binding.disconnectButton.setEnabled(true);
                binding.layoutLoadingId.getRoot().setVisibility(View.GONE);
                break;
            case ACQUISITION_OK:
                binding.stopButton.setEnabled(true);
                binding.startButton.setEnabled(false);
                break;
            case DISCONNECTED:
                binding.startButton.setEnabled(false);
                binding.stopButton.setEnabled(false);
                binding.disconnectButton.setEnabled(false);
                binding.connectButton.setEnabled(true);
                break;
        }
    }*/

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.connect_button:
                try {
                    biTalinoCommunication.connect(device.getAddress());
                } catch (BITalinoException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.disconnect_button:
                try {
                    biTalinoCommunication.disconnect();
                } catch (BITalinoException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.start_button:
                try {
                    biTalinoCommunication.start(new int[]{0, 1, 2, 3, 4, 5}, 1);
                } catch (BITalinoException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.stop_button:
                try {
                    biTalinoCommunication.stop();
                } catch (BITalinoException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.state_button:
                try {
                    biTalinoCommunication.state();
                } catch (BITalinoException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
