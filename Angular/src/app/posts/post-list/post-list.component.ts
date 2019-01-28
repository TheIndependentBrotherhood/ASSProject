import { Component, OnInit, OnDestroy } from '@angular/core';
import { PageEvent } from '@angular/material';
import { Subscription } from 'rxjs';

import { Post } from '../post.model';
import { PostsService } from '../posts.service';

@Component ({
  selector: 'app-post-list'
  , templateUrl: './post-list.component.html'
  , styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit, OnDestroy {
  /* posts = [
    {title: 'First Post', content: 'This is the first post\'s content'}
    , {title: 'Second Post', content: 'This is the second post\'s content'}
    , {title: 'Third Post', content: 'This is the third post\'s content'}
  ]; */
  posts: Post[] = [];
  isLoading = false;
  postsPerPage = 5;
  currentPage = 1;
  pageSizeOptions = [5, 10, 25, 50, 100, 250];
  private postsSub: Subscription;

  constructor(public postsService: PostsService) {}

  ngOnInit() {
    this.isLoading = true;
    this.postsService.getPosts(this.postsPerPage, this.currentPage);
    this.postsSub = this.postsService.getPostUpdateListener()
      .subscribe((posts: Post[]) => {
        this.isLoading = false;
        this.posts = posts;
      });
  }

  ngOnDestroy() {
    this.postsSub.unsubscribe();
  }

  onChangedPage(pageData: PageEvent) {
    this.currentPage = pageData.pageIndex + 1;
    this.postsPerPage = pageData.pageSize;
    this.postsService.getPosts(this.postsPerPage, this.currentPage);
  }

  onDelete(postId: string) {
    this.postsService.deletePost(postId);
  }
}
