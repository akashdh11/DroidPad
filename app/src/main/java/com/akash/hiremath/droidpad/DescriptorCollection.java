package com.akash.hiremath.droidpad;

public class DescriptorCollection {

    public static final int MOUSE_ID= 1;
    public static final int GAMEPAD_ID= 2;
    public static final int KEYBOARD_ID= 3;

    public static final byte[] MOUSE_KEYBOARD_GAMEPAD_COMBO = new byte[]{
            // Mouse descriptor
            0x05, 0x01,                    // USAGE_PAGE (Generic Desktop)
            0x09, 0x02,                    // USAGE (Mouse)
            (byte) 0xa1, 0x01,             // COLLECTION (Application)
            (byte) 0x85, 0x01,             //   REPORT_ID (Mouse)
            0x09, 0x01,                    //   USAGE (Pointer)
            (byte) 0xa1, 0x00,             //   COLLECTION (Physical)
            0x05, 0x09,                    //     USAGE_PAGE (Button)
            0x19, 0x01,                    //     USAGE_MINIMUM (Button 1)
            0x29, 0x03,                    //     USAGE_MAXIMUM (Button 3)
            0x15, 0x00,                    //     LOGICAL_MINIMUM (0)
            0x25, 0x01,                    //     LOGICAL_MAXIMUM (1)
            (byte) 0x95, 0x03,             //     REPORT_COUNT (3)
            0x75, 0x01,                    //     REPORT_SIZE (1)
            (byte) 0x81, 0x02,             //     INPUT (Data,Var,Abs)
            (byte) 0x95, 0x01,             //     REPORT_COUNT (1)
            0x75, 0x05,                    //     REPORT_SIZE (5)
            (byte) 0x81, 0x03,             //     INPUT (Cnst,Var,Abs)
            0x05, 0x01,                    //     USAGE_PAGE (Generic Desktop)
            0x09, 0x30,                    //     USAGE (X)
            0x09, 0x31,                    //     USAGE (Y)
            0x15, (byte) 0x81,             //     LOGICAL_MINIMUM (-127)
            0x25, 0x7f,                    //     LOGICAL_MAXIMUM (127)
            0x75, 0x08,                    //     REPORT_SIZE (8)
            (byte) 0x95, 0x02,             //     REPORT_COUNT (2)
            (byte) 0x81, 0x06,             //     INPUT (Data,Var,Rel)
            (byte) 0xc0,                   //   END_COLLECTION
            (byte) 0xc0,                   // END_COLLECTION

            ////////////////////////////////////  GAMEPAD    //////////////////////////////////////
            (byte) 0x05, (byte) 0x01,           //USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x05,           //USAGE (Game Pad)
            (byte) 0xA1, (byte) 0x01,           //COLLECTION (Application)
            (byte) 0xA1, (byte) 0x00,           //   COLLECTION (Physical)
            (byte) 0x85, (byte) 0x02,           //        REPORT_ID (2)
            (byte) 0x05, (byte) 0x09,           //        USAGE_PAGE (Button)
            (byte) 0x19, (byte) 0x01,           //        USAGE_MINIMUM (Button 1)
            (byte) 0x29, (byte) 0x08,           //        USAGE_MAXIMUM (Button 8)
            (byte) 0x15, (byte) 0x00,           //        LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,           //        LOGICAL_MAXIMUM (1)
            (byte) 0x75, (byte) 0x01,           //        REPORT_SIZE (1)
            (byte) 0x95, (byte) 0x08,           //        REPORT_COUNT (8)
            (byte) 0x81, (byte) 0x02,           //        INPUT (Data,Var,Abs)
            (byte) 0x05, (byte) 0x09,           //        USAGE_PAGE (Button)
            (byte) 0x19, (byte) 0x09,           //        USAGE_MINIMUM (Button 9)
            (byte) 0x29, (byte) 0x10,           //        USAGE_MAXIMUM (Button 16)
            (byte) 0x15, (byte) 0x00,           //        LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,           //        LOGICAL_MAXIMUM (1)
            (byte) 0x75, (byte) 0x01,           //        REPORT_SIZE (1)
            (byte) 0x95, (byte) 0x08,           //        REPORT_COUNT (8)
            (byte) 0x81, (byte) 0x02,           //        INPUT (Data,Var,Abs)
            (byte) 0x05, (byte) 0x01,           //        USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x30,           //        USAGE (X)
            (byte) 0x09, (byte) 0x31,           //        USAGE (Y)
            (byte) 0x09, (byte) 0x32,           //        USAGE (Z)
            (byte) 0x09, (byte) 0x33,           //        USAGE (Rx)
            (byte) 0x09, (byte) 0x34,           //        USAGE (Ry)
            (byte) 0x09, (byte) 0x35,           //        USAGE (Rz)
            (byte) 0x15, (byte) 0x81,           //        LOGICAL_MINIMUM (-127)
            (byte) 0x25, (byte) 0x7f,           //        LOGICAL_MAXIMUM (127)
            (byte) 0x75, (byte) 0x08,           //        REPORT_SIZE (8)
            (byte) 0x95, (byte) 0x06,           //        REPORT_COUNT (6)
            (byte) 0x81, (byte) 0x02,           //        INPUT (Data,Var,Abs)
            (byte) 0xC0,                        //    END_COLLECTION
            (byte) 0xC0,                        //END_COLLECTION

            ////////////////////////////////////  KEYBOARD    //////////////////////////////////////
            (byte) 0x05, (byte) 0x01,                         // USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x06,                         // Usage (Keyboard)
            (byte) 0xA1, (byte) 0x01,                         // Collection (Application)
            (byte) 0x85, (byte) 0x03,                         //   REPORT_ID (Keyboard)
            (byte) 0x05, (byte) 0x07,                         //     Usage Page (Key Codes)
            (byte) 0x19, (byte) 0xe0,                         //     Usage Minimum (224)
            (byte) 0x29, (byte) 0xe7,                         //     Usage Maximum (231)
            (byte) 0x15, (byte) 0x00,                         //     Logical Minimum (0)
            (byte) 0x25, (byte) 0x01,                         //     Logical Maximum (1)
            (byte) 0x75, (byte) 0x01,                         //     Report Size (1)
            (byte) 0x95, (byte) 0x08,                         //     Report Count (8)
            (byte) 0x81, (byte) 0x02,                         //     Input (Data, Variable, Absolute)
            (byte) 0x95, (byte) 0x01,                         //     Report Count (1)
            (byte) 0x75, (byte) 0x08,                         //     Report Size (8)
            (byte) 0x81, (byte) 0x01,                         //     Input (Constant) reserved byte(1)
            (byte) 0x95, (byte) 0x01,                         //     Report Count (1)
            (byte) 0x75, (byte) 0x08,                         //     Report Size (8)
            (byte) 0x15, (byte) 0x00,                         //     Logical Minimum (0)
            (byte) 0x25, (byte) 0x65,                         //     Logical Maximum (101)
            (byte) 0x05, (byte) 0x07,                         //     Usage Page (Key codes)
            (byte) 0x19, (byte) 0x00,                         //     Usage Minimum (0)
            (byte) 0x29, (byte) 0x65,                         //     Usage Maximum (101)
            (byte) 0x81, (byte) 0x00,                         //     Input (Data, Array) Key array(6 bytes)
            (byte) 0xc0                                       // End Collection (Application)

    };


    public static final byte[] XBox360 = new byte[]{
            (byte) 0x05, (byte) 0x01,                    // USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x05,                    // USAGE (Game Pad)
            (byte) 0xa1, (byte) 0x01,                    // COLLECTION (Application)
            (byte) 0x05, (byte) 0x01,                    //   USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x3a,                    //   USAGE (Counted Buffer)
            (byte) 0xa1, (byte) 0x02,                    //   COLLECTION (Logical)
            (byte) 0x75, (byte) 0x08,                    //     REPORT_SIZE (8)
            (byte) 0x95, (byte) 0x02,                    //     REPORT_COUNT (2)
            (byte) 0x05, (byte) 0x01,                    //     USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x3f,                    //     USAGE (Reserved)
            (byte) 0x09, (byte) 0x3b,                    //     USAGE (Byte Count)
            (byte) 0x81, (byte) 0x01,                    //     INPUT (Cnst,Ary,Abs)
            (byte) 0x75, (byte) 0x01,                    //     REPORT_SIZE (1)
            (byte) 0x15, (byte) 0x00,                    //     LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,                    //     LOGICAL_MAXIMUM (1)
            (byte) 0x35, (byte) 0x00,                    //     PHYSICAL_MINIMUM (0)
            (byte) 0x45, (byte) 0x01,                    //     PHYSICAL_MAXIMUM (1)
            (byte) 0x95, (byte) 0x04,                    //     REPORT_COUNT (4)
            (byte) 0x05, (byte) 0x09,                    //     USAGE_PAGE (Button)
            (byte) 0x19, (byte) 0x0c,                    //     USAGE_MINIMUM (Button 12)
            (byte) 0x29, (byte) 0x0f,                    //     USAGE_MAXIMUM (Button 15)
            (byte) 0x81, (byte) 0x02,                    //     INPUT (Data,Var,Abs)
            (byte) 0x75, (byte) 0x01,                    //     REPORT_SIZE (1)
            (byte) 0x15, (byte) 0x00,                    //     LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,                    //     LOGICAL_MAXIMUM (1)
            (byte) 0x35, (byte) 0x00,                    //     PHYSICAL_MINIMUM (0)
            (byte) 0x45, (byte) 0x01,                    //     PHYSICAL_MAXIMUM (1)
            (byte) 0x95, (byte) 0x04,                    //     REPORT_COUNT (4)
            (byte) 0x05, (byte) 0x09,                    //     USAGE_PAGE (Button)
            (byte) 0x09, (byte) 0x09,                    //     USAGE (Button 9)
            (byte) 0x09, (byte) 0x0a,                    //     USAGE (Button 10)
            (byte) 0x09, (byte) 0x07,                    //     USAGE (Button 7)
            (byte) 0x09, (byte) 0x08,                    //     USAGE (Button 8)
            (byte) 0x81, (byte) 0x02,                    //     INPUT (Data,Var,Abs)
            (byte) 0x75, (byte) 0x01,                    //     REPORT_SIZE (1)
            (byte) 0x15, (byte) 0x00,                    //     LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,                    //     LOGICAL_MAXIMUM (1)
            (byte) 0x35, (byte) 0x00,                    //     PHYSICAL_MINIMUM (0)
            (byte) 0x45, (byte) 0x01,                    //     PHYSICAL_MAXIMUM (1)
            (byte) 0x95, (byte) 0x03,                    //     REPORT_COUNT (3)
            (byte) 0x05, (byte) 0x09,                    //     USAGE_PAGE (Button)
            (byte) 0x09, (byte) 0x05,                    //     USAGE (Button 5)
            (byte) 0x09, (byte) 0x06,                    //     USAGE (Button 6)
            (byte) 0x09, (byte) 0x0b,                    //     USAGE (Button 11)
            (byte) 0x81, (byte) 0x02,                    //     INPUT (Data,Var,Abs)
            (byte) 0x75, (byte) 0x01,                    //     REPORT_SIZE (1)
            (byte) 0x95, (byte) 0x01,                    //     REPORT_COUNT (1)
            (byte) 0x81, (byte) 0x01,                    //     INPUT (Cnst,Ary,Abs)
            (byte) 0x75, (byte) 0x01,                    //     REPORT_SIZE (1)
            (byte) 0x15, (byte) 0x00,                    //     LOGICAL_MINIMUM (0)
            (byte) 0x25, (byte) 0x01,                    //     LOGICAL_MAXIMUM (1)
            (byte) 0x35, (byte) 0x00,                    //     PHYSICAL_MINIMUM (0)
            (byte) 0x45, (byte) 0x01,                    //     PHYSICAL_MAXIMUM (1)
            (byte) 0x95, (byte) 0x04,                    //     REPORT_COUNT (4)
            (byte) 0x05, (byte) 0x09,                    //     USAGE_PAGE (Button)
            (byte) 0x19, (byte) 0x01,                    //     USAGE_MINIMUM (Button 1)
            (byte) 0x29, (byte) 0x04,                    //     USAGE_MAXIMUM (Button 4)
            (byte) 0x81, (byte) 0x02,                    //     INPUT (Data,Var,Abs)
            (byte) 0x75, (byte) 0x08,                    //     REPORT_SIZE (8)
            (byte) 0x15, (byte) 0x00,                    //     LOGICAL_MINIMUM (0)
            (byte) 0x26, (byte) 0xff, (byte) 0x00,       //     LOGICAL_MAXIMUM (255)
            (byte) 0x35, (byte) 0x00,                    //     PHYSICAL_MINIMUM (0)
            (byte) 0x46, (byte) 0xff, (byte) 0x00,       //     PHYSICAL_MAXIMUM (255)
            (byte) 0x95, (byte) 0x02,                    //     REPORT_COUNT (2)
            (byte) 0x05, (byte) 0x01,                    //     USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x32,                    //     USAGE (Z)
            (byte) 0x09, (byte) 0x35,                    //     USAGE (Rz)
            (byte) 0x81, (byte) 0x02,                    //     INPUT (Data,Var,Abs)
            (byte) 0x75, (byte) 0x10,                    //     REPORT_SIZE (16)
            (byte) 0x16, (byte) 0x00, (byte) 0x80,       //     LOGICAL_MINIMUM (-32768)
            (byte) 0x26, (byte) 0xff, (byte) 0x7f,       //     LOGICAL_MAXIMUM (32767)
            (byte) 0x36, (byte) 0x00, (byte) 0x80,       //     PHYSICAL_MINIMUM (-32768)
            (byte) 0x46, (byte) 0xff, (byte) 0x7f,       //     PHYSICAL_MAXIMUM (32767)
            (byte) 0x05, (byte) 0x01,                    //     USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x01,                    //     USAGE (Pointer)
            (byte) 0xa1, (byte) 0x00,                    //     COLLECTION (Physical)
            (byte) 0x95, (byte) 0x02,                    //       REPORT_COUNT (2)
            (byte) 0x05, (byte) 0x01,                    //       USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x30,                    //       USAGE (X)
            (byte) 0x09, (byte) 0x31,                    //       USAGE (Y)
            (byte) 0x81, (byte) 0x02,                    //       INPUT (Data,Var,Abs)
            (byte) 0xc0,                                 //     END_COLLECTION
            (byte) 0x05, (byte) 0x01,                    //     USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x01,                    //     USAGE (Pointer)
            (byte) 0xa1, (byte) 0x00,                    //     COLLECTION (Physical)
            (byte) 0x95, (byte) 0x02,                    //       REPORT_COUNT (2)
            (byte) 0x05, (byte) 0x01,                    //       USAGE_PAGE (Generic Desktop)
            (byte) 0x09, (byte) 0x33,                    //       USAGE (Rx)
            (byte) 0x09, (byte) 0x34,                    //       USAGE (Ry)
            (byte) 0x81, (byte) 0x02,                    //       INPUT (Data,Var,Abs)
            (byte) 0xc0,                                 //     END_COLLECTION
            (byte) 0xc0,                                 //   END_COLLECTION
            (byte) 0xc0                                  // END_COLLECTION

    };
}