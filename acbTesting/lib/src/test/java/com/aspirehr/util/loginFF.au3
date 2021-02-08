;~ -------------------------------------------------------------------------------
;~ Login_dialog.au3
;~ To handle the Authentication Dialogbox
;~ Create By: Raghu Kiran
;~ Usage: LoginFF.au3 "Dialog Title<substr>" "User Name" "Password" "OK" ; to click OK
;~        LoginFF.au3 "Dialog Title<substr>" "User Name" "Password" "Cancel" ; to click Cancel
;~ Return: 0 - Successful Authentication
;~         1 - invalid arguments by user
;~         2 - invalid credentials given by user, login dialog reappears 
;~             simulate clicking cancel
;~         3 - No Authenticaion window poped up!!!
;~ -------------------------------------------------------------------------------
#include <WinAPI.au3>

AutoItSetOption("WinTitleMatchMode","2") ;1=start, 2=subStr, 3=exact, 4=advanced, -1 to -4=Nocase
AutoItSetOption("WinSearchChildren","1") ;0=no, 1=search children also

if $CmdLine[0] < 4 then
   ;msgbox(0,"Error","Supply all the Arguments, Dialog title User Name, Password")
   Exit 1 ; User does not get how to use the script!!!
EndIf

WinWait($CmdLine[1],"",20); match the window with substring provided by user

If WinExists($CmdLine[1]) Then ; if window exists 
 WinActivate($CmdLine[1]) ;Activate the latest window with provided Title
 ; $hwnd = WinGetHandle($CmdLine[1])
 ;MsgBox(0, "Window Class Name is ... ", _WinAPI_GetClassName($hwnd))
 Send($CmdLine[2],1) ; type user name allowing special Characters!
 Sleep(200);
 send("{TAB}")  ; simulate pressing TAB key
 Send($CmdLine[3],1) ; Type password llowing special Characters like ! @ # etc
 Sleep(200);
 
	; Depending on the class of window, we might have to click TAB once or more, 
	; for instance, IE you need to click Tab twice since you have "remember me" check box
	If StringInStr(_WinAPI_GetClassName(WinGetHandle($CmdLine[1])), "Chrome_WidgetWin", 2) Then
	   ; Google Chrome browser Auth window
	ElseIf StringInStr(_WinAPI_GetClassName(WinGetHandle($CmdLine[1])), "32770", 2) Then
	   ; Internet Explorer 6 Broswer Auth Window
	   send("{TAB}")  ; simulate pressing TAB key to highlight the focus on RADIO button
	   $BooleanIEbrowser =True;
	ElseIf StringInStr(_WinAPI_GetClassName(WinGetHandle($CmdLine[1])), "MozillaDialogClass", 2) Then
	   ; Mozilla Firefox Broswer Auth Window
	Else
	   ;Do nothing for now. Could be some other browser like opera or newer version of IE broswer!;
	EndIf
 
 send("{TAB}")  ; simulate pressing TAB key to highlight the focus on OK button
 
	; now the focus is on OK button in auth window. 
	; You will have to click TAB once more to click cancel
	If StringInStr($CmdLine[4], "cancel", 2) Then
	   Sleep(200);
	   send("{TAB}")  ; simulate pressing TAB key to highlight the focus on CANCEL button
	EndIf
 
 send("{ENTER}")  ; Simulate pressing ENTER Key
 
 ;~ Perform Some Post Script Diagnostics. Lets wait for a second or two and see if the dialog pops up again.
 ;~ If window pops up again, lets return Exit Status of the Script as 2 for now! meaning invalid creds
 Sleep(1000)
 If WinExists($CmdLine[1]) Then ; if window exists 
	send("{TAB}")
	send("{TAB}")
	send("{TAB}")
	send("{ENTER}")
	Exit 2; Invalid credentials provided by user, let user call Script again, for now Exit!
 Else
	Exit 0 ; A proper Exit after correct authentication! Assuming there is no window after 1 second!
 EndIf
Else
   Exit 3 ;There was no Authentication Dialog window at all!!
EndIf
	;Exit 3 ; My own code to Say that there was no Authentication Popup with given title even after 10 seconds!!!
Exit 0; Stop the script