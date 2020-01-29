/*


<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:app="http://schemas.android.com/apk/res-auto"
            xmlns:tools="http://schemas.android.com/tools"
            android:paddingBottom="@dimen/activity_vertical_margin"
            android:paddingLeft="@dimen/activity_horizontal_margin"
            android:paddingRight="@dimen/activity_horizontal_margin"
            android:paddingTop="@dimen/activity_vertical_margin"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            tools:context=".ContactActivity">

<TextView
        android:id="@+id/textView3"
                android:layout_width="380dp"
                android:layout_height="wrap_content"
                android:text="TextView"
                tools:text="Superb" />

<EditText
        android:id="@+id/url"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="10dp"
                android:ems="10"
                android:hint="@string/hint"
                android:inputType="textUri" />

<Button
        android:id="@+id/button"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_alignLeft="@+id/url"
                android:layout_below="@+id/url"
                android:layout_marginTop="10dp"
                android:onClick="onButtonClicked"
                android:text="@string/button_text" />

<ScrollView
        android:id="@+id/scrollView"
                android:layout_width="99dp"
                android:layout_height="wrap_content"
                android:layout_below="@+id/button"
                android:layout_alignLeft="@+id/url">

<LinearLayout
            android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:orientation="vertical">

<WebView
                android:id="@+id/webView"
                        android:layout_width="match_parent"
                        android:layout_height="match_parent" />
</LinearLayout>
</ScrollView>
</RelativeLayout>




 */