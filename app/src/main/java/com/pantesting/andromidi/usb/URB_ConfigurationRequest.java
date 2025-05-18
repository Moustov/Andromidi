package com.pantesting.andromidi.usb;

public class URB_ConfigurationRequest extends USBRequestBlock {
    public byte[] URB_transfer_type = {0x00};

    public URB_ConfigurationRequest(){
        this.Packet_Data = new byte[] {0x00};
    }

}
