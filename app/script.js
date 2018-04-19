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
 * @category  UI interaction
 * @package   Basic Joystick Input Device
 * @author    Diogo Cordeiro <diogo@fc.up.pt>
 * @copyright 2018 Diogo Cordeiro.
 * @license   http://www.fsf.org/licensing/licenses/agpl-3.0.html GNU Affero General Public License version 3.0
 * @link      https://www.diogo.site/projects/basic-joystick-input-device/
 */

// ON and OFF leds srcs
let LED_OFF = "data:image/png;base64,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";
let LED_ON  = "data:image/png;base64,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";

let SQUARECSS = "border: solid darkred";

let current = "10";
//***********************************

/**
 * Create leds
 */
for (let i = 1; i <= 3; ++i)
{
        for (let j = 0; j < 3; ++j)
        {
                // Led holder
                let figure = document.createElement("figure");
                figure.setAttribute("class", "box");

                // Led
                let img = document.createElement("img");

                img.src = LED_OFF;
                img.setAttribute("value", "OFF");

                img.id = "led" + i+j;
                img.setAttribute("class", "led");
                img.setAttribute("onclick", "switch_led("+i+j+")");
                figure.appendChild(img);

                document.getElementById("row"+i).appendChild(figure);
        }
}
// Draw the first square
document.getElementById("led10").setAttribute("style", SQUARECSS);

/**
 * Turn current answer bits accordingly
 */
function switch_led(led_id)
{
        let image = document.getElementById("led"+led_id);
        if (image.getAttribute("value") == "ON")
        {
                image.setAttribute("value", "OFF");
                image.src = LED_OFF;
        }
        else
        {
                image.setAttribute("value", "ON");
                image.src = LED_ON;
        }
}

document.onkeydown = handleKey;

function handleKey(e) {

        e = e || window.event;

        if (e.keyCode == '38')
        { // up arrow
                move_square("up");
        }
        else if (e.keyCode == '40')
        { // down arrow
                move_square("down");
        }
        else if (e.keyCode == '37')
        { // left arrow
                move_square("left");
        }
        else if (e.keyCode == '39')
        { // right arrow
                move_square("right");
        }
        else if (e.keyCode == '13')
        { // Enter key
                switch_led(current);
        }
}

function move_square(to)
{
        switch (to)
        {
        case "up":
                if (current.charAt(0) == '1')
                {
                        return;
                }
                to_id = String.fromCharCode(current.charCodeAt(0)-1)+current.charAt(1);
                document.getElementById("led"+current).setAttribute("style", "");
                document.getElementById("led"+to_id).setAttribute("style", SQUARECSS);
                current = to_id;

                break;
        case "right":
                if (current.charAt(1) == '2')
                {
                        return;
                }
                to_id = current.charAt(0)+String.fromCharCode(current.charCodeAt(1)+1);
                document.getElementById("led"+current).setAttribute("style", "");
                document.getElementById("led"+to_id).setAttribute("style", SQUARECSS);
                current = to_id;

                break;
        case "down":
                if (current.charAt(0) == '3')
                {
                        return;
                }
                to_id = String.fromCharCode(current.charCodeAt(0)+1)+current.charAt(1);
                document.getElementById("led"+current).setAttribute("style", "");
                document.getElementById("led"+to_id).setAttribute("style", SQUARECSS);
                current = to_id;

                break;
        case "left":
                if (current.charAt(1) == '0')
                {
                        return;
                }
                to_id = current.charAt(0)+String.fromCharCode(current.charCodeAt(1)-1);
                document.getElementById("led"+current).setAttribute("style", "");
                document.getElementById("led"+to_id).setAttribute("style", SQUARECSS);
                current = to_id;

                break;
        }
}
