import React from 'react';
import {Router, Route, Switch, Redirect} from "react-router";
import routes from './router/index.js'
import history from './router/history.js'

function App() {
  return (
    <>
      <Router history={history}>
        <Switch>
          {/*配置默认的首页*/}
          <Redirect exact from="/" to={"/home/index"}/>
          {/*其他路由*/}
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
    </>
  );
}

export default App;
