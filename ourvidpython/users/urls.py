from django.urls import path

from .views import home_view, signup_view, dashboard_view, video_view, new_video

app_name = "users"

urlpatterns = [
    path('', home_view, name='home'),
    path('signup/', signup_view, name='sign-up'),
    path('dashboard/', dashboard_view, name='dashboard'),
    path('video/<int:id>', video_view.as_view(), name='video'),
    path('new_video', new_video.as_view(), name='new_video')
]
