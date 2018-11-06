// IMyAidlInterface.aidl
package com.amit.aidl;

import com.amit.aidl.StudentInfo;

//parcelable StudentInfo;
interface IMyAidlInterface {

   StudentInfo getName();
   void setName(in StudentInfo st);
   int addNumbers(in int ValueFirst, in int valueSecond);
}
