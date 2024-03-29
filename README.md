# Pre-work - *SimpleTodo*

**SimpleTodo** is an android app that allows building a todo list; it includes basic todo item management functionality like adding new items as well as editing or deleting existing items.

Submitted by: **Jonathan Combs**

Time spent: **about 4.5** hours spent in total

## User Stories

The following **required** functionality is completed:

* [X] User can **view a list of todo items**
* [X] User can **successfully add and remove items** from the todo list
* [X] User's **list of items persisted** upon modification and and retrieved properly on app restart

The following **optional** features are implemented:

* [x] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list

The following **additional** features are implemented:

* [x] Screen looks good sideways

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='walkthrough.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [Peek](https://github.com/phw/peek).

## Notes

Had a few odd issues with installing the new version of Android Studio, notably getting the AVD working (had to rename a weird file named "05-reset-dirs-sample.conf", don't ask). Also, I found out on the 14th that the due date had been bumped up a day, so that was a surprise.
Also, recording the gif turned out to be a pain. All I needed was to use ```sudo dnf install ffmpeg -y``` to get Peek to work, but it took me a while to figure that out.
Edit: finally found a half-hour to switch the main activity to RelativeLayout and make it not terrible when tilted sideways. (and re-record the gif)

## License

    Copyright [2020] [Jonathan Combs]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
