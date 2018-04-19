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
 * @category  Input Device
 * @package   Basic Joystick Input Device
 * @author    Diogo Cordeiro <diogo@fc.up.pt>
 * @copyright 2018 Diogo Cordeiro.
 * @license   http://www.fsf.org/licensing/licenses/agpl-3.0.html GNU Affero General Public License version 3.0
 * @link      https://www.diogo.site/projects/basic-joystick-input-device/
 */

// Arduino pin numbers
const int JoyStick_X = A0;           // analog pin connected to X output
const int JoyStick_Y = A1;           // analog pin connected to Y output
const int JoyStick_button = 6;       // digital pin connected to switch output

void
setup()
{
        pinMode(JoyStick_X, INPUT);
        pinMode(JoyStick_Y, INPUT);
        pinMode(JoyStick_button, INPUT_PULLUP);
        Serial.begin(9600);          // 9600 bps
}

void
loop()
{
        int x      = analogRead(JoyStick_X),
            y      = analogRead(JoyStick_Y),
            button = digitalRead(JoyStick_button);
        Serial.print(x, DEC);
        Serial.print(",");
        Serial.print(y, DEC);
        Serial.print(",");
        Serial.println(button);

        if (y > 900)
                Serial.println("up");

        if (x < 30)
                Serial.println("right");

        if (y < 30)
                Serial.println("down");

        if (x > 900)
                Serial.println("left");

        if (button == 0)
                Serial.println("click");

        delay(100);
}
