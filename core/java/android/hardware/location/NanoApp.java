/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.hardware.location;

import android.annotation.SystemApi;
import android.os.Parcel;
import android.os.Parcelable;

/** A class describing nano apps.
 * A nano app is a piece of executable code that can be
 * downloaded onto a specific architecture. These are targtted
 * for low power compute domains on a device.
 *
 * Nano apps are expected to be used only by bundled apps only
 * at this time.
 *
 * @hide
 */
@SystemApi
public class NanoApp {
    private String mPublisher;
    private String mName;

    private int mAppId;
    private int mAppVersion;

    private int mNeededReadMemBytes;
    private int mNeededWriteMemBytes;
    private int mNeededExecMemBytes;

    private int[] mNeededSensors;
    private int[] mOutputEvents;
    private byte[] mAppBinary;

    public NanoApp() {
    }

    /**
     * Set the publisher name
     *
     * @param publisher name of the publisher of this nano app
     */
    public void setPublisher(String publisher) {
        mPublisher = publisher;
    }

    /**
     * set the name of the app
     *
     * @param name   name of the app
     */
    public void setName(String name) {
        mName = name;
    }

    /**
     * set the app identifier
     *
     * @param appId  add identifier
     */
    public void setAppId(int appId) {
        mAppId = appId;
    }

    /**
     * Set the app version
     *
     * @param appVersion app version
     */
    public void setAppVersion(int appVersion) {
        mAppVersion = appVersion;
    }

    /**
     * set memory needed as read only
     *
     * @param neededReadMemBytes
     *               read only memory needed in bytes
     */
    public void setNeededReadMemBytes(int neededReadMemBytes) {
        mNeededReadMemBytes = neededReadMemBytes;
    }

    /**
     * set writable memory needed in bytes
     *
     * @param neededWriteMemBytes
     *               writable memory needed in bytes
     */
    public void setNeededWriteMemBytes(int neededWriteMemBytes) {
        mNeededWriteMemBytes = neededWriteMemBytes;
    }

    /**
     * set executable memory needed
     *
     * @param neededExecMemBytes
     *               executable memory needed in bytes
     */
    public void setNeededExecMemBytes(int neededExecMemBytes) {
        mNeededExecMemBytes = neededExecMemBytes;
    }

    /**
     * set the sensors needed for this app
     *
     * @param neededSensors
     *               needed Sensors
     */
    public void setNeededSensors(int[] neededSensors) {
        mNeededSensors = neededSensors;
    }

    public void setOutputEvents(int[] outputEvents) {
        mOutputEvents = outputEvents;
    }

    /**
     * set output events returned by the nano app
     *
     * @param appBinary generated events
     */
    public void setAppBinary(byte[] appBinary) {
        mAppBinary = appBinary;
    }


    /**
     * get the publisher name
     *
     * @return publisher name
     */
    public String getPublisher() {
      return mPublisher;
    }

    /**
     * get the name of the app
     *
     * @return app name
     */
    public String getName() {
    return mName;
    }

    /**
     * get the identifier of the app
     *
     * @return identifier for this app
     */
    public int getAppId() {
      return mAppId;
    }

    /**
     * get the app version
     *
     * @return app version
     */
    public int getAppVersion() {
      return mAppVersion;
    }

    /**
     * get the ammount of readable memory needed by this app
     *
     * @return readable memory needed in bytes
     */
    public int getNeededReadMemBytes() {
      return mNeededReadMemBytes;
    }

    /**
     * get the ammount og writable memory needed in bytes
     *
     * @return writable memory needed in bytes
     */
    public int getNeededWriteMemBytes() {
      return mNeededWriteMemBytes;
    }

    /**
     * executable memory needed in bytes
     *
     * @return executable memory needed in bytes
     */
    public int getNeededExecMemBytes() {
      return mNeededExecMemBytes;
    }

    /**
     * get the sensors needed by this app
     *
     * @return sensors needed
     */
    public int[] getNeededSensors() {
      return mNeededSensors;
    }

    /**
     * get the events generated by this app
     *
     * @return generated events
     */
    public int[] getOutputEvents() {
      return mOutputEvents;
    }

    /**
     * get the binary for this app
     *
     * @return app binary
     */
    public byte[] getAppBinary() {
      return mAppBinary;
    }

    private NanoApp(Parcel in) {
        mPublisher = in.readString();
        mName = in.readString();

        mAppId = in.readInt();
        mAppVersion = in.readInt();
        mNeededReadMemBytes = in.readInt();
        mNeededWriteMemBytes = in.readInt();
        mNeededExecMemBytes = in.readInt();

        int mNeededSensorsLength = in.readInt();
        mNeededSensors = new int[mNeededSensorsLength];
        in.readIntArray(mNeededSensors);

        int mOutputEventsLength = in.readInt();
        mOutputEvents = new int[mOutputEventsLength];
        in.readIntArray(mOutputEvents);

        int binaryLength = in.readInt();
        mAppBinary = new byte[binaryLength];
        in.readByteArray(mAppBinary);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(mPublisher);
        out.writeString(mName);
        out.writeInt(mAppId);
        out.writeInt(mAppVersion);
        out.writeInt(mNeededReadMemBytes);
        out.writeInt(mNeededWriteMemBytes);
        out.writeInt(mNeededExecMemBytes);

        out.writeInt(mNeededSensors.length);
        out.writeIntArray(mNeededSensors);

        out.writeInt(mOutputEvents.length);
        out.writeIntArray(mOutputEvents);

        out.writeInt(mAppBinary.length);
        out.writeByteArray(mAppBinary);
    }

    public static final Parcelable.Creator<NanoApp> CREATOR
            = new Parcelable.Creator<NanoApp>() {
        public NanoApp createFromParcel(Parcel in) {
            return new NanoApp(in);
        }

        public NanoApp[] newArray(int size) {
            return new NanoApp[size];
        }
    };
}
