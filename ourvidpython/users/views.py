from django.shortcuts import render, redirect
from django.contrib.auth import authenticate, login
from django.urls import include, path 
from django.contrib.auth.forms import UserCreationForm
from django.views.generic.base import View, HttpResponseRedirect, HttpResponse
from django.contrib import messages
from django.contrib.auth.decorators import login_required
import string, random
from django.core.files.storage import FileSystemStorage
import os
from wsgiref.util import FileWrapper


from .models import Video, Comment
from .forms import SignUpForm, CommentForm, NewVideoForm

@login_required
def dashboard_view(request):
	return render(request, 'app/dashboard.html')


def home_view(request):
	return render(request, 'app/home.html')


def signup_view(request):
	if request.user.is_authenticated:
		return redirect('users:dashboard')
	if request.method == "POST":
		form = SignUpForm(request.POST)
		if form.is_valid():
			form.save()
			username = form.cleaned_data.get('username')
			password = form.cleaned_data.get('password1')
			user = authenticate(username=username, password=password)
			login(request, user)
			return redirect('users:dashboard')
		else:
			messages.error(request, 'Correct the errors below')
	else:
		form = SignUpForm()

	return render(request, 'app/signup.html', {'form': form})

class view_video(View):
	
	def get(self, request, file_name):
		BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
		file = FileWrapper(open(BASE_DIR+'/'+file_name, 'rb'))
		response = HttpResponse(file, content_type='video/mp4')
		response['Content-Disposition'] = 'attachment; filename={}'.format(file_name)
		return response

class video_view(View):
	template_name = 'app/video.html'

	def get(self, request, id):
		#fetch video from DB by ID
		video_by_id = Video.objects.get(id=id)
		BASE_DIR = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
		video_by_id.path = 'http://localhost:8000/get_video/'+video_by_id.path
		context = {'video':video_by_id}
		
		if request.user.is_authenticated:
			print('user signed in')
			comment_form = CommentForm()
			context['form'] = comment_form

		
		comments = Comment.objects.filter(video__id=id).order_by('-datetime')[:5]
		print(comments)
		context['comments'] = comments
		return render(request, self.template_name, context)

# class comment_view(View):
#     template_name = 'app/comment.html'

#     def post(self, request):
#         # pass filled out HTML-Form from View to CommentForm()
#         form = CommentForm(request.POST)
#         if form.is_valid():
#             # create a Comment DB Entry
#             text = form.cleaned_data['text']
#             video_id = request.POST['video']
#             video = Video.objects.get(id=video_id)
			
#             new_comment = Comment(text=text, user=request.user, video=video)
#             new_comment.save()
#             return HttpResponseRedirect('/video/{}'.format(str(video_id)))
#         return HttpResponse('This is Register view. POST Request.')

class new_video(View):
	template_name = 'app/new_video.html'

	def get(self, request):
		
		form = NewVideoForm()
		return render(request, self.template_name, {'form':form})

	def post(self, request):
		# pass filled out HTML-Form from View to NewVideoForm()
		form = NewVideoForm(request.POST, request.FILES)       

		if form.is_valid():
			# create a new Video Entry
			title = form.cleaned_data['title']
			description = form.cleaned_data['description']
			file = form.cleaned_data['file']

			random_char = ''.join(random.choices(string.ascii_uppercase + string.digits, k=10))
			path = random_char+file.name

			fs = FileSystemStorage(location = os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
			filename = fs.save(path, file)
			file_url = fs.url(filename)

			print(fs)
			print(filename)
			print(file_url)

			new_video = Video(title=title, 
							description=description,
							user=request.user,
							path=path)
			new_video.save()
			
			# redirect to detail view template of a Video
			return HttpResponseRedirect('/video/{}'.format(new_video.id))
		else:
			return HttpResponse('Your form is not valid. Go back and try again.')