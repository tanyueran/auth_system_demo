import React, {Suspense} from 'react';
import {Router, Route, Switch, Redirect} from "react-router";
import {} from 'antd';
import routes from './router/index.js'
import history from './router/history.js'
import MLoading from './components/MLoading.js'


function App() {
  return (
    <>
      <Suspense fallback={<MLoading/>}>
        <Router history={history}>
          <Switch>
            {/*配置默认的首页*/}
            <Redirect exact from="/" to={"/home/index"}/>
            {
              routes.map((route, i) =>
                <Route exact={route.exact} key={i} path={route.path}
                       render={
                         props =>
                           <route.component {...props} routes={route.children}/>}
                />
              )
            }
          </Switch>
        </Router>
      </Suspense>
    </>
  );
}

export default App;
