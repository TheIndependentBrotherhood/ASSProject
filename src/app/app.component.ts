import { Component } from '@angular/core';

import { Post } from './posts/post.model';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  storedPosts: Post[] = [];

  onPostAdded(post) {
    // this.storedPosts.push(3); Generate an error in IDE because '3' isn't assigneable to the Post's model (Number != String)
    this.storedPosts.push(post);
  }
}
