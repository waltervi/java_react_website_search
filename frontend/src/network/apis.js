
export const API = {
    getUsers : ( successCallback, errorCallBack) => {
        fetch("/api/users")
        .then(res => res.json())
        .then(
          (result) => successCallback(result),
          (error) => errorCallBack(error)
        )
    },

    findUserByName : ( username, successCallback, errorCallBack) => {
        const url = '/api/users/search/findByName?name=' + username;
        fetch(url , {
          method: 'GET',
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          }
        })
        .then(response => response.json())
        .then(
          (result) => {
            if (result._embedded.users.length == 0) {
              this.setState({showUsernameNotFound : true});
            }
            else {
              this.findFriend(result._embedded.users[0].id);
            }
          }
        )
    },

    addFriend : ( request, successCallback, errorCallBack) => {
        fetch('/api/friendShips', {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json',
            },
            body: JSON.stringify(request)
          })
          .then(response => response.json())
          .then(
            (result) => successCallback(result),
            (error) => errorCallBack(error)
          );
    },

    findFriend: ( friendName, successCallback, errorCallBack) => {
        fetch('/api/users/search/findByName?name=' + friendName , {
            method: 'GET',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(
            (result) => successCallback(result),
            (error) => errorCallBack(error)
        )
    } ,

    findExpert : (  id, textSearch, successCallback, errorCallBack ) => {
        const url = '/api/experts/byTopic?userId=' + id + '&topic=' + textSearch;
        console.log(url);
        fetch(url, {
            method: 'GET',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            }
        })
        .then(response => response.json())
        .then(
            (result) => successCallback(result),
            (error) => errorCallBack(error)
        );        
    },

    findFriends: (userID, successCallback, errorCallBack) =>{
        const url = '/api/friendList?userId=' + userID;
        console.log(url);
        fetch(url, {
            method: 'GET',
            headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            }
        })
        .then(response => response.json())
        .then(
            (result) => successCallback(result),
            (error) => errorCallBack(error)
        );
    }
}