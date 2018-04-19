/*
 * Basic Joystick Input Device - a very simple and cool way of getting to know how a joystick works underneath the hood.
 * Copyright (C) 2018, Diogo Cordeiro.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * @category  Driver
 * @package   Basic Joystick Input Device
 * @author    Diogo Cordeiro <up201705417@fc.up.pt>
 * @copyright 2018 Diogo Cordeiro.
 * @license   http://www.fsf.org/licensing/licenses/agpl-3.0.html GNU Affero General Public License version 3.0
 * @link      https://www.diogo.site/projects/basic-joystick-input-device/
 */

/*****************************************************************************
 *                             NOTICE                                        *
 * This file was originally written for a IR Controller project I did, thus  *
 * the different indent style from the remaining codebase                    *
 *****************************************************************************/

package basicJoystickInputDeviceDriver;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.io.IOException;
import java.util.Enumeration;

/**
 * serialPort.java
 *
 * Diogo Cordeiro
 * diogo@fc.up.pt
 *
 * Allows a Java Application to interface with an arduino board by means of
 * the serial port.
 */
public class Main implements SerialPortEventListener
{
  SerialPort serialPort;

  // The port we're normally going to use
  private static final String PORT_NAMES[] =
    {
      "/dev/ttyACM0", // Raspberry Pi
      "/dev/ttyUSB0", // Linux
      "COM3", // Windows
      "/dev/tty.usbserial-A9007UX1", // Mac OS X
      "/dev/cu.usbmodem1411",
      "/dev/cu.usbmodem1451",
    };

  // A BufferedReader which will be fed by a InputStreamReader
  // converting the bytes into characters
  // making the displayed results codepage independent
  private BufferedReader input;

  // The output stream to the port
  private OutputStream output;

  // Milliseconds to block while waiting for port open
  private static final int TIME_OUT = 2000;

  // Default bits per second for COM port
  private static final int DATA_RATE = 9600;

  public void initialize()
  {
      System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
    CommPortIdentifier portId = null;
    Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

    // First, Find an instance of serial port as set in PORT_NAMES
    while (portEnum.hasMoreElements())
      {
        CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
        for (String portName : PORT_NAMES)
          {
            if (currPortId.getName().equals(portName))
              {
                portId = currPortId;
                break;
              }
          }
      }

    if (portId == null)
      {
        System.out.println("Could not find COM port.");
        return;
      }

    try
      {
        // open serial port, and use class name for the appName
        serialPort = (SerialPort) portId.open(this.getClass().getName(),
            TIME_OUT);

        // set port parameters
        serialPort.setSerialPortParams(DATA_RATE, //Transfer rate of the serial port
            SerialPort.DATABITS_8, //rate of 10 bits 8 (sending)
            SerialPort.STOPBITS_1, //rate of 10 bits 1 (receiver)
            SerialPort.PARITY_NONE); //receive and send data

        // open the streams
        input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
        output = serialPort.getOutputStream();

        // add event listeners
        serialPort.addEventListener(this);
        serialPort.notifyOnDataAvailable(true);
      }
    catch (Exception e)
      {
        System.err.println(e.toString());
      }
  }

  /**
   * This should be called when you stop using the port.
   * This will prevent port locking on platforms like Linux.
   */
  public synchronized void close()
  {
    if (serialPort != null)
      {
        serialPort.removeEventListener();
        serialPort.close();
      }
  }

  /**
   * Handle an event on the serial port. Read the data and print it.
   */
  public synchronized void serialEvent(SerialPortEvent oEvent)
  {
    if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE)
      {
        try
          {
            String inputLine=null;
            if (input.ready())
              {
                inputLine = input.readLine();
                Handle handle = new Handle(inputLine);
              }
          }
        catch (Exception e)
          {
            System.err.println(e.toString());
          }
      }
    // Ignore all the other eventTypes, but you should consider the other ones
  }

  /**
   * Sends data to the serial port.
   * @param data - Value to be sent
   */
  public void sendData(int data)
  {
    try
      {
        output.write(data); // writes value on the serial port to be sent
      }
    catch (IOException e)
      {
        System.err.println(e.toString());
      }
  }

  public static void main(String[] args) throws Exception
  {
    Main main = new Main();
    main.initialize();
    Thread t=new Thread()
      {
        public void run()
          {
            // the following line will keep this app alive for
            // 1000 seconds, waiting for events to occur and
            // responding to them (printing incoming messages
            // to console)
            try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
          }
      };
    t.start();
    System.out.println("Started");
  }
}
