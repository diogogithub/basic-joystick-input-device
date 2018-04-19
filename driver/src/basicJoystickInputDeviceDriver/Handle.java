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

package basicJoystickInputDeviceDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * serialPort.java
 *
 * Diogo Cordeiro
 * up201705417@fc.up.pt
 *
 * Allows a Java Application to interface with an arduino board by means of
 * the serial port.
 */
public class
Handle
{
        public
        Handle(String button)
        {
                try
                {
                        Robot robot = new Robot();

                        // Interfacing with the app
                        switch (button)
                        {
                        case "up":
                                System.out.println("Para cima: " + button);
                                robot.keyPress(KeyEvent.VK_KP_UP);
                                robot.keyRelease(KeyEvent.VK_KP_UP);

                                break;
                        case "right":
                                System.out.println("Para a direita: " + button);
                                robot.keyPress(KeyEvent.VK_KP_RIGHT);
                                robot.keyRelease(KeyEvent.VK_KP_RIGHT);

                                break;
                        case "down":
                                System.out.println("Para baixo: " + button);
                                robot.keyPress(KeyEvent.VK_KP_DOWN);
                                robot.keyRelease(KeyEvent.VK_KP_DOWN);

                                break;
                        case "left":
                                System.out.println("Para a esquerda: " + button);
                                robot.keyPress(KeyEvent.VK_KP_LEFT);
                                robot.keyRelease(KeyEvent.VK_KP_LEFT);

                                break;
                        case "click":
                                System.out.println("Clique: " + button);
                                robot.keyPress(KeyEvent.VK_ENTER);
                                robot.keyRelease(KeyEvent.VK_ENTER);

                                break;
                        default:
                                System.out.println("Leitura do input analogico atual: " + button);
                        }
                }
                catch(AWTException e)
                {
                        e.printStackTrace();
                }
        }
}
