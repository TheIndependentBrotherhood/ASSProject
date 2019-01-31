import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DataTableComponent } from './data-table/data-table.component';
import { PostListComponent } from './posts/post-list/post-list.component';
import { PostCreateComponent } from './posts/post-create/post-create.component';
import { AuthGuard } from './auth/auth.guard';

const routes: Routes = [
  { path: '', component: DataTableComponent }
  , { path: 'create', component: PostCreateComponent, canActivate: [AuthGuard] }
  , { path: 'posts', component: PostListComponent }
  , { path: 'edit/:postId', component: PostCreateComponent, canActivate: [AuthGuard] }
  , { path: 'auth', loadChildren: './auth/auth.module#AuthModule'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule {

}
