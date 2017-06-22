/*
 * Copyright (C) 2016 AriaLyy(https://github.com/AriaLyy/Aria)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arialyy.aria.core.command;

import android.util.Log;
import com.arialyy.aria.core.inf.IEntity;
import com.arialyy.aria.core.inf.ITask;
import com.arialyy.aria.core.inf.AbsTaskEntity;

/**
 * Created by lyy on 2016/8/22.
 * 添加任务的命令
 */
class AddCmd<T extends AbsTaskEntity> extends AbsCmd<T> {

  AddCmd(String targetName, T entity) {
    super(targetName, entity);
  }

  @Override public void executeCmd() {
    if (!canExeCmd) return;
    ITask task = mQueue.getTask(mTaskEntity.getEntity());
    if (task == null) {
      mTaskEntity.getEntity().setState(IEntity.STATE_WAIT);
      mQueue.createTask(mTargetName, mTaskEntity);
    } else {
      Log.w(TAG, "添加命令执行失败，【该任务已经存在】");
    }
  }
}