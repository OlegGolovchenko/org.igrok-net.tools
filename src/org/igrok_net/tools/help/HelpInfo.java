/*
 *  IgRok-NET tools
 *  Copyright (C) 2023  Oleg Golovchenko
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.igrok_net.tools.help;

public class HelpInfo {
    private static final String HEADER = "IgRok-NET Tools  Copyright (C) 2023  Oleg Golovchenko\r\n" + //
            "This program comes with ABSOLUTELY NO WARRANTY; see extracted license file.\r\n" + //
            "This is free software, and you are welcome to redistribute it\r\n" + //
            "under certain conditions; see extracted license file.\r\n" + //
            "If license file is not included see <https://www.gnu.org/licenses/gpl-3.0.html#license-text>";

    public HelpInfo() {
        super();
    }

    public void PrintHeader() {
        System.out.println(HEADER);
    }
}
