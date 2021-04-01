from django import forms
from django.contrib.auth.forms import UserCreationForm
from django.contrib.auth.models import User

class SignUpForm(UserCreationForm):
	email = forms.EmailField(
		label='',
		max_length=254,
		widget=forms.EmailInput(
			attrs={
				"placeholder": "Email",
				"class": "form-control"
			}
		)
	)

	username = forms.CharField(
		label='',
		max_length=30,
		min_length=5,
		required=True,
		widget=forms.TextInput(
			attrs={
				"placeholder": "Username",
				"class": "form-control"
			}
		)
	)

	password1 = forms.CharField(
		label='',
		max_length=30,
		min_length=8,
		required=True,
		widget=forms.PasswordInput(
			attrs={
				"placeholder": "Password",
				"class": "form-control"
			}
		)
	)

	password2 = forms.CharField(
		label='',
		max_length=30,
		min_length=8,
		required=True,
		widget=forms.PasswordInput(
			attrs={
				"placeholder": "Confirm Password",
				"class": "form-control"
			}
		)
	)
	
	class Meta:
		model = User
		fields = ('username', 'email', 'password1', 'password2')

class CommentForm(forms.Form):
    text = forms.CharField(label='Text', max_length=500)
    #video = forms.IntegerField(widget=forms.HiddenInput(), initial=1) 
    
class NewVideoForm(forms.Form):
    title = forms.CharField(label='Title', max_length=700)
    description = forms.CharField(label='Description', max_length=300)
    file = forms.FileField()
