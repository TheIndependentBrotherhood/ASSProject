import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DataTableComponent } from './data-table/data-table.component';
import { PostListComponent } from './posts/post-list/post-list.component';
import { PostCreateComponent } from './posts/post-create/post-create.component';

const routes: Routes = [
  { path: '', component: DataTableComponent },
  { path: 'create', component: PostCreateComponent },
  { path: 'posts', component: PostListComponent },
  { path: 'edit/:postId', component: PostCreateComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {

}
