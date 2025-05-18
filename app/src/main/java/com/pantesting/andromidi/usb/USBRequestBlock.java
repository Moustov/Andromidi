package com.pantesting.andromidi.usb;

/**
 * Class to handle USBRequestBlock messages over USB.
 *
 * /!\ byte vectors are in little endian mode (https://en.wikipedia.org/wiki/Endianness)
 * therefore 0x000b should be encoded like {0x0b, 0x00}
 */
public class USBRequestBlock {

    public byte[] USBPcap_pseudoheader = {0x00, 0x00};
    public byte[] IRP_ID = {0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00};
    public byte[] IR_USBD_STATUS = {0x00, 0x00, 0x00, 0x00};

    /** URB Functions
     * - URB_FUNCTION_GET_DESCRIPTOR_FROM_DEVICE (0x000b)
     * - URB_FUNCTION_CONTROL_TRANSFER (0x0008)
     * - URB_FUNCTION_SELECT_CONFIGURATION (0x0000)
     * */
    public byte[] URB_Function = {0x00, 0x00};

    /**
     * 0x00 = Direction: FDO -> PDO
     * 0x01 = Direction: PDO -> FDO
     */
    public byte[] IRP_information = {0x00};
    public byte[] URB_bus_id = {0x00, 0x00};
    public byte[] Device_address = {0x00, 0x00};

    /**
     * 0x00, Direction: OUT
     * 0x01, Direction: IN
     */
    public byte[] Endpoint = {0x00};

    /**
     * : URB_CONTROL (0x02)
     */
    public byte[] URB_transfer_type = {0x00};
    public byte[] Packet_Data_Length = {0x00, 0x00, 0x00, 0x00};
    public byte[] Packet_Data;

}
