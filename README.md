# OnBoardingLib
A simple project to make building user on-boarding screens a little less painful.


The main focus of this project are the AbstractOnboardingDialog.java and the OnboardingRelativeLayout.java classes. 

The Dialog is meant to cover the entire screen but allows for up to 4 views to be passed to it. 

When passing a view into the dialog we then use Paint and PorterDuff modes to "cut"/"erase" a whole in the Dialogs background. This allows for the underlying view (which was passed to the dialog) to be shown through the dialog itself thus blocking the user from doing anything else on the screen until they complete the desired action.
