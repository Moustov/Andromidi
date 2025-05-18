Matribox II Pro controller from Sonicake 
===

This project aims to drive the [Matribox II Pro by SONICAKE](https://www.sonicake.com/pages/matribox-ii-pro-software-firmware-1).

# Background
When scanning the USB messages with Wireshark, there are different values that can help you understanding 
the USB API used by the software delivered by Sonicake to drive the Matribox. 


## URB_BULK messages (USB Request Blocks)
````
//                                                                                                                                                                    v ID session USB (device address)
//                                                                                                                                                                                  v IN
//                                                                                                                                                                                        vv endpoint number
//                                                                                                                                                                                v 10000011  endpoint
//                                                                                                                                                                                           v URB
//                        vvv  vvv size of the message (27 bytes)
//                                    vv          vv    vv          vv          vv          vv          vv          vv 0xffff848289c1c010 : IRP ID
//                                                                                                                                                     vv 0x0: IN  / 0x1: OUT
//                                                                                                                                           v   URB Function: URB_FUNCTION_BULK_OR_INTERRUPT_TRANSFER (0x0009)
//byte[] urb_bulk_in3 = {0x1b, 0x0, 0x10, (byte)0x90, 0x67, (byte)0xaf, (byte)0x82, (byte)0x84, (byte)0xff, (byte)0xff, 0x0, 0x0, 0x0, 0x0, 0x9, 0x0, 0x0, 0x2, 0x0, 0x16, 0x0, (byte)0x83, 0x3, 0x0, 0x0, 0x0, 0x0};
````

### URB Function: URB_FUNCTION_GET_DESCRIPTOR_FROM_DEVICE (0x000b)
Used to discover devices
````
0000   1c 00 00 00 00 00 00 00 00 00 00 00 00 00 0b 00
0010   00 02 00 1a 00 80 02 08 00 00 00 00 80 06 00 02
0020   00 00 b5 01
````

### URB Function: URB_FUNCTION_SELECT_CONFIGURATION (0x0000)
Used to discover devices
````
0000   1c 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00
0010   00 02 00 1a 00 00 02 08 00 00 00 00 00 09 01 00
0020   00 00 00 00
````

### URB Function: URB_FUNCTION_BULK_OR_INTERRUPT_TRANSFER (0x0009)
> https://learn.microsoft.com/en-us/windows-hardware/drivers/ddi/usb/ns-usb-_urb_bulk_or_interrupt_transfer
 
### URB Function: URB_FUNCTION_ABORT_PIPE (0x0002) 

### All URB functions from Microsoft
See https://learn.microsoft.com/en-us/windows-hardware/drivers/ddi/usb/

* _URB_BULK_OR_INTERRUPT_TRANSFER: The _URB_BULK_OR_INTERRUPT_TRANSFER structure is used by USB client drivers to send or receive data on a bulk pipe or on an interrupt pipe.
* _URB_CONTROL_DESCRIPTOR_REQUEST: The _URB_CONTROL_DESCRIPTOR_REQUEST structure is used by USB client drivers to get or set descriptors on a USB device.
* _URB_CONTROL_FEATURE_REQUEST: The _URB_CONTROL_FEATURE_REQUEST structure is used by USB client drivers to set or clear features on a device, interface, or endpoint.
* _URB_CONTROL_GET_CONFIGURATION_REQUEST: The _URB_CONTROL_GET_CONFIGURATION_REQUEST structure is used by USB client drivers to retrieve the current configuration for a device.
* _URB_CONTROL_GET_INTERFACE_REQUEST: The _URB_CONTROL_GET_INTERFACE_REQUEST structure is used by USB client drivers to retrieve the current alternate interface setting for an interface in the current configuration.
* _URB_CONTROL_GET_STATUS_REQUEST: The _URB_CONTROL_GET_STATUS_REQUEST structure is used by USB client drivers to retrieve status from a device, interface, endpoint, or other device-defined target.
* _URB_CONTROL_TRANSFER: The _URB_CONTROL_TRANSFER structure is used by USB client drivers to transfer data to or from a control pipe.
* _URB_CONTROL_TRANSFER_EX: The _URB_CONTROL_TRANSFER_EX structure is used by USB client drivers to transfer data to or from a control pipe, with a timeout that limits the acceptable transfer time.
* _URB_CONTROL_VENDOR_OR_CLASS_REQUEST: The _URB_CONTROL_VENDOR_OR_CLASS_REQUEST structure is used by USB client drivers to issue a vendor or class-specific command to a device, interface, endpoint, or other device-defined target.
* _URB_GET_CURRENT_FRAME_NUMBER: The _URB_GET_CURRENT_FRAME_NUMBER structure is used by USB client drivers to retrieve the current frame number.
* _URB_GET_ISOCH_PIPE_TRANSFER_PATH_DELAYS: The _URB_GET_ISOCH_PIPE_TRANSFER_PATH_DELAYS structure is used by USB client drivers to retrieve delays associated with isochronous transfer programming in the host controller and transfer completion so that the client driver can ensure that the device gets the isochronous packets in time.
* _URB_HEADER: The _URB_HEADER structure is used by USB client drivers to provide basic information about the request being sent to the host controller driver.
* _URB_ISOCH_TRANSFER: The _URB_ISOCH_TRANSFER structure is used by USB client drivers to send data to or retrieve data from an isochronous transfer pipe.
* _URB_OPEN_STATIC_STREAMS: The _URB_OPEN_STATIC_STREAMS structure is used by a USB client driver to open streams in the specified bulk endpoint.
* _URB_OS_FEATURE_DESCRIPTOR_REQUEST: The _URB_OS_FEATURE_DESCRIPTOR_REQUEST structure is used by the USB hub driver to retrieve Microsoft OS Feature Descriptors from a USB device or an interface on a USB device.
* _URB_PIPE_REQUEST: The _URB_PIPE_REQUEST structure is used by USB client drivers to clear a stall condition on an endpoint.
* _URB_SELECT_CONFIGURATION: The _URB_SELECT_CONFIGURATION structure is used by client drivers to select a configuration for a USB device.
* _URB_SELECT_INTERFACE: The _URB_SELECT_INTERFACE structure is used by USB client drivers to select an alternate setting for an interface or to change the maximum packet size of a pipe in the current configuration on a USB device.

## SysEx
You may use Matribox II Pro Software V1.0.0 for Windows.zip to drive
your Matribox, but if you use [Wireshark](https://www.wireshark.org/) to scan USB messages,
you will see SYSEX messages

> **SysEx** : *System Exclusive Messages* are messages send information about a synthesizer's functions, 
> rather than performance data such as which notes are being played and how loud.
> Because they can include functionality beyond what the MIDI standard provides, they are a major reason 
> for the flexibility and longevity of the MIDI standard. Manufacturers use them to create proprietary messages
> that control their equipment more thoroughly than the limitations of standard MIDI messages.
> The MIDI Manufacturers Association issues a unique identification number to MIDI companies.
> These are included in SysEx messages, to ensure that only the specifically addressed device responds to the message, 
> while all others know to ignore it. Many instruments also include a SysEx ID setting, so a controller 
> can address two devices of the same model independently.

> SYSEX message example when "DRUM" button is activated
> ````
> 0000   1b 00 10 f0 98 4a 09 ae ff ff 00 00 00 00 09 00
> 0010   01 02 00 0f 00 83 03 48 00 00 00 04 f0 21 25 04
> 0020   4d 50 00 04 00 7b 00 04 14 00 00 04 00 00 01 04
> 0030   00 00 00 04 00 01 01 04 00 0c 00 04 00 00 00 04
> 0040   00 00 01 04 09 00 00 04 08 01 00 04 00 00 00 04
> 0050   00 00 00 04 00 00 00 04 00 00 01 04 01 00 00 07
> 0060   00 00 f7
> ````

* SysEx messages start with (hexadecimal) `F0` 
* then there are 1 to 3 bytes (`00` -> `7F` values) for the manufacturer ID
* then X bytes (`00` -> `7F` values)
* ends with `F7`

## Universal Real Time SysEx messages
Universal Real Time SysEx messages start with `F0`, followed by `7F`, then include other fields before the terminating `F7`.

The following shows Universal Real Time SysEx message format (all numbers hexadecimal):

> `F0 7F` <Device-ID> <Sub-ID#1> [<Sub-ID#2> [\<parameters\>]] `F7`

where `Device-ID` (aka "*MMC device's ID*" or "*channel number*") is a value ranging from `00` to `7F` (`7F` = all devices)

> **MMC** : *MIDI Machine Control* is a subset of the MIDI specification.
> It provides specific commands for controlling recording equipment 
> such as multi-track recorders. 
> MMC messages can be sent along a standard MIDI cable for remote control 
> of such functions as Play, Fast Forward, Rewind, Stop, Pause, and Record. 
> These are "System Exclusive" ([SysEx](https://en.wikipedia.org/wiki/MIDI#System_Exclusive_messages)) messages, 
> specifically Real Time Universal SysEx messages. 


and `Sub-ID#1:` is one of the following values. The bolded values are MIDI Machine Control related:
* 01 = Long Form MTC
* 02 = MIDI Show Control
* 03 = Notation Information
* 04 = Device Control
* 05 = Real Time MTC Cueing
* 06 = **MIDI Machine Control Command**
* 07 = **MIDI Machine Control Response**
* 08 = Single Note Retune 

### Analysis
Knowing this, the sample provided above seems to embed a couple of extra data

* SysEx messages are embedded into URB packets
* there is an `FO` at byte 0003 and another one at byte  001C with only one `F7` at the end :
> 0000   1b 00 10 f0 98 4a 09 ae ff ff 00 00 00 00 09 00 <br>
> 0010   01 02 00 0f 00 83 03 48 00 00 00 04 **f0** 21 25 04 <br>
> 0020   4d 50 00 04 00 7b 00 04 14 00 00 04 00 00 01 04 <br>
> 0030   00 00 00 04 00 01 01 04 00 0c 00 04 00 00 00 04 <br>
> 0040   00 00 01 04 09 00 00 04 08 01 00 04 00 00 00 04 <br>
> 0050   00 00 00 04 00 00 00 04 00 00 01 04 01 00 00 07 <br>
> 0060   00 00 **f7** <br>
* according to the standard, the Matribox ID should be 0x21 (33) and the sub ID 0x25 (37)
   > the decode_sysex() method provides a sequence that starts with `[0x21, 0x25, 0x4D, 0x50, 0x00, 0x00, ...]` (i.e. `[33, 37, 77, 80, 0, 0, ...]`)
   
    It seems actually that every SysEx message send to the Matribox include always starts with the byte sequence `[0x21, 0x25, 0x4D, 0x50, ...]`
* there are `0x04` values that are inserted at columns 3, 7, 11, 15 (counting from 0) - this means the dump is actually an event packet  
 => the final dump in Wireshark "MIDI System Exclusive Command" should be retrieved

> Here is a sample of pure MIDI System Exclusive Command dump :
> ````
> 0000   f0 21 25 4d 50 00 00 1a 00 14 00 00 00 00 01 00   .!%MP...........
> 0010   00 00 00 01 01 00 0c 00 00 00 00 00 00 01 09 00   ................
> 0020   00 08 05 00 00 00 00 00 01 00 00 00 00 00 00 01   ................
> 0030   01 00 00 00 00 f7                                 ......
> ````

# Message Control Changes
The user manual refers to a MIDI Control Information List.

Instead of using SysEx messages, "control_change" message types can be involved
````python
import mido

message = mido.Message('control_change', channel=0, control=59, value=60)
output_port = mido.open_output("Matribox II PRO MIDI 1")
output_port.send(message)
````

This way, controls and values are the one found in the list found in the manual.

# Ref
* SYSEX 101: https://www.youtube.com/watch?v=JKJMeHwydUQ
* https://midi.org
* Matribox user manual at page 55 ([French version of the user manual](https://cdn.shopify.com/s/files/1/0100/1950/4185/files/QME-200_Manual-FR.pdf?v=1728524183)), there is a "MIDI Control Information List"
* https://electronicmusic.fandom.com/wiki/System_exclusive
* https://en.wikipedia.org/wiki/MIDI_Machine_Control
* https://github.com/johnko/python-rtmidi/blob/master/examples/sendsysex.py
* https://www.zem-college.de/midi/mc_scm1.htm
* https://learn.microsoft.com/pdf?url=https%3A%2F%2Flearn.microsoft.com%2Fen-us%2Fwindows-hardware%2Fdrivers%2Fddi%2F_usbref%2Ftoc.json
* URB : 
  * https://learn.microsoft.com/en-us/windows-hardware/drivers/ddi/usb/ns-usb-_urb
  * https://www.linkedin.com/pulse/comprehensive-guide-usb-urbs-david-zhu-rcb0c/