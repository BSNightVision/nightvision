/*
 * NotSoSimpleTimeZone.java  -  A SimpleTimeZone always in DST.
 * Copyright (C) 2011-2023 Brian Simpson
 * This file is part of Night Vision.
 *
 * Night Vision is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Night Vision is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Night Vision.  If not, see <http://www.gnu.org/licenses/>.
 */


package com.nvastro.nvj;

import java.util.Date;
import java.util.SimpleTimeZone;
import java.util.TimeZone;


/** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
 * Extends SimpleTimeZone so that it is always in DST.
 *
 * @author Brian Simpson
 */
@SuppressWarnings("serial")
public class NotSoSimpleTimeZone extends SimpleTimeZone {
  static final private int HOUR = 3600000;  // 1 hour in milliseconds

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Constructor.
   *
   * @param rawOffset Base time zone offset in milliseconds to GMT
   * @param ID Time zone name that is given to this instance
   */
  public NotSoSimpleTimeZone(int rawOffset, String ID) {
    // Note:  Can lie now:   super(rawOffset + HOUR, ID);
    //                       -> getOffset returns rawOffset
    //                       -> getRawOffset returns rawOffset - HOUR
    //        or lie later:  super(rawOffset, ID);
    //                       -> getOffset returns rawOffset + HOUR
    //                       -> getRawOffset returns rawOffset
    // Both ways should work as far as getting the correct times
    // from a GregorianCalendar, but only the first method did.
    super(rawOffset + HOUR, ID);
  }

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Constructor.
   *
   * @param tz TimeZone object
   */
  public NotSoSimpleTimeZone(TimeZone tz) {
    this(tz.getRawOffset(), tz.getID());
  }

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Override of equals.
   * Checks if specified NotSoSimpleTimeZone is equal.
   *
   * @param obj Specified NotSoSimpleTimeZone
   */
  @Override
  public boolean equals(Object obj) {
    if ( obj instanceof NotSoSimpleTimeZone ) return super.equals(obj);
    return false;
  }

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Override of hasSameRules.
   * Checks if specified NotSoSimpleTimeZone has same rules.
   *
   * @param tz Specified NotSoSimpleTimeZone
   */
  @Override
  public boolean hasSameRules(TimeZone tz) {
    if ( tz instanceof NotSoSimpleTimeZone ) return super.hasSameRules(tz);
    return false;
  }

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Override of getDSTSavings.
   *
   * @return DST offset (1 hour) in milliseconds
   */
  @Override
  public int getDSTSavings() { return HOUR; }

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Override of setDSTSavings to set DST offset (argument ignored).
   *
   * @param millisSavedDuringDST DST offset in milliseconds (ignored)
   */
  @Override
  public void setDSTSavings(int millisSavedDuringDST) {}

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Override of getOffset.
   */
  @Override
  public int getOffset(int era, int year, int month, int day,
                       int dayOfWeek, int millis) {
    return super.getRawOffset();
  }

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Override of getRawOffset.
   */
  @Override
  public int getRawOffset() { return super.getRawOffset() - HOUR; }

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Override of inDaylightTime.
   *
   * @param date Date object (ignored)
   * @return True always
   */
  @Override
  public boolean inDaylightTime(Date date) { return true; }

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Override of useDaylightTime.
   *
   * @param date Date object (ignored)
   * @return True always
   */
  public boolean useDaylightTime(Date date) { return true; }

  /** <!--~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~-->
   * Override of toString.
   */
  @Override
  public String toString() {
    return "nvj.NotSoSimpleTimeZone(Always-in-DST)[" +
           "id=\"" + getID() + "\",rawoffset=" + getRawOffset() +
           ",actualoffset=" + super.getRawOffset() +
           ",dstSavings=" + getDSTSavings() + ",useDaylight=true]";
  }
}

